package com.github.shaggydemiurge.movieapp.data.preferences

import android.content.SharedPreferences
import com.github.shaggydemiurge.movieapp.data.util.fromJson
import com.github.shaggydemiurge.movieapp.data.util.toJson
import com.squareup.moshi.Moshi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BasePreferences(
    protected val preferences: SharedPreferences,
    protected val moshi: Moshi,
) {

    protected fun booleanPreference(key: String, default: Boolean = false) =
        preference(
            key,
            getter = { getBoolean(it, default) },
            setter = { k, v -> putBoolean(k, v) }
        )

    protected fun intPreference(key: String, default: Int = 0) =
        preference(
            key,
            getter = { getInt(it, default) },
            setter = { k, v -> putInt(k, v) }
        )

    protected fun longPreference(key: String, default: Long = 0L) =
        preference(
            key,
            getter = { getLong(it, default) },
            setter = { k, v -> putLong(k, v) }
        )

    protected fun stringPreference(key: String, default: String = "") =
        preference(
            key,
            getter = { getString(it, default) ?: default },
            setter = { k, v -> putString(k, v) }
        )

    protected inline fun <T, reified R> objectPreference(
        key: String,
        crossinline toSerializable: (T) -> R,
        crossinline fromSerializable: (R) -> T,
        default: T,
    ) = preference(
        key,
        getter = { k ->
            getString(k, null)?.let { json ->
                try {
                    fromSerializable(moshi.fromJson<R>(json) ?: return@let default)
                } catch (e: Exception) {
                    default
                }
            } ?: default
        },
        setter = { k, value ->
            putString(k, moshi.toJson(toSerializable(value)))
        }
    )

    protected fun stringSetPreference(key: String, default: Set<String> = emptySet()) =
        preference(
            key,
            getter = { getStringSet(it, default) ?: default },
            setter = { k, v -> putStringSet(k, v) }
        )

    protected fun stringMapPreference(
        key: String,
        separator: String = KV_SEPARATOR,
        default: Map<String, String> = emptyMap(),
    ) = preference(
        key,
        getter = {
            getStringSet(it, null)?.associate { string ->
                val (k, v) = string.split(separator, limit = 2)
                k to v
            } ?: default
        },
        setter = { k, v ->
            putStringSet(
                k,
                v.map { (entryKey, entryValue) ->
                    entryKey + separator + entryValue
                }.toSet()
            )
        }
    )

    protected inline fun <reified T : Enum<T>> enumPreference(key: String, default: T? = null) =
        preference(
            key,
            getter = {
                getString(it, "")
                    ?.takeIf { name -> name.isNotEmpty() }
                    ?.let { name -> enumValueOf(name) }
                    ?: default
            },
            setter = { k, v -> putString(k, v?.name ?: "") }
        )

    fun SharedPreferences.readStringSet(key: String): Set<String> =
        getStringSet(key, setOf()) ?: setOf()

    fun SharedPreferences.saveStringSet(key: String, value: Set<String>) =
        save { it.putStringSet(key, value) }

    private fun SharedPreferences.save(action: (SharedPreferences.Editor) -> Unit) {
        edit().also {
            action.invoke(it)
            it.apply()
        }
    }

    protected fun <T> preference(
        key: String,
        getter: SharedPreferences.(key: String) -> T,
        setter: SharedPreferences.Editor.(key: String, value: T) -> Unit,
    ) = PreferenceProperty(key, getter, setter)

    protected inner class PreferenceProperty<T>(
        private val key: String,
        private val getter: SharedPreferences.(key: String) -> T,
        private val setter: SharedPreferences.Editor.(key: String, value: T) -> Unit,
    ) : ReadWriteProperty<Any, T> {

        override fun getValue(thisRef: Any, property: KProperty<*>): T =
            getter(preferences, key)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            preferences.edit()
                .also { it.setter(key, value) }
                .apply()
        }

        fun nullable() = object : ReadWriteProperty<Any, T?> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T? =
                if (preferences.contains(key)) {
                    getter(preferences, key)
                } else null

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
                preferences.edit()
                    .also { editor ->
                        if (value == null) {
                            editor.remove(key)
                        } else {
                            editor.setter(key, value)
                        }
                    }
                    .apply()
            }
        }
    }

    companion object {
        const val KV_SEPARATOR = "::"
    }
}
