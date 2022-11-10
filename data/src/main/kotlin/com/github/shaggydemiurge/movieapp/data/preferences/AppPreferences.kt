package com.github.shaggydemiurge.movieapp.data.preferences

import android.content.SharedPreferences
import com.github.shaggydemiurge.movieapp.core.entities.Configuration
import com.github.shaggydemiurge.movieapp.data.entities.preferences.ConfigurationPreference
import com.squareup.moshi.Moshi

class AppPreferences(sharedPreferences: SharedPreferences, moshi: Moshi) : BasePreferences(sharedPreferences, moshi) {

    var configuration by objectPreference<Configuration?, ConfigurationPreference?>(
        key = "configuration",
        toSerializable = { data ->
            data?.let {
                ConfigurationPreference(it.baseImageUrl, it.posterSize)
            }
        },
        fromSerializable = { pref ->
            pref?.let {
                Configuration(it.baseImageUrl, it.posterSize)
            }
        },
        default = null
    )
}
