package com.yuriy.weather.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yuriy.weather.R
import com.yuriy.weather.application.AppClass
import com.yuriy.weather.data.WeatherRepository

class SettingsActivity : AppCompatActivity(), Preference.OnPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Toast.makeText(AppClass.applicationContext(), "Test toast", Toast.LENGTH_LONG).show()
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        WeatherRepository.getInstance().updateData()
        return true
    }


    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}