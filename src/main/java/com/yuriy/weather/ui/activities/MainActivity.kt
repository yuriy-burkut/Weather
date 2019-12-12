package com.yuriy.weather.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuriy.weather.R
import com.yuriy.weather.data.WeatherRepository
import com.yuriy.weather.ui.fragments.DetailsFragment
import com.yuriy.weather.ui.fragments.ListFragment
import com.yuriy.weather.ui.viewmodels.WeatherViewModel
import com.yuriy.weather.ui.viewmodels.WeatherViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val listFragment by lazy { ListFragment() }
    private val detailsFragment by lazy { DetailsFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val factory = WeatherViewModelFactory(WeatherRepository.getInstance())
        val viewModel = ViewModelProviders.of(this, factory)
            .get(WeatherViewModel::class.java)

        if (id_main_fragment_container != null) {

            viewModel.getWeather()
                .observe(this, Observer { weather ->
                    id_current_temp.text = weather.main.temp.roundToInt().toString().plus("\u2103")
                    id_city.text = weather.city
                })

            with(supportFragmentManager) {
                beginTransaction().replace(R.id.id_main_fragment_container, listFragment).commit()
            }
        } else {
            with(supportFragmentManager) {
                beginTransaction().replace(R.id.id_list_container, listFragment).commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_nav_settings -> openSettings()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openSettings(): Boolean {

        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        return true
    }
}
