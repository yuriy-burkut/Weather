package com.yuriy.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuriy.weather.R
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.ui.adapters.WeatherAdapter
import com.yuriy.weather.ui.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[WeatherViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.weatherForecast.observe(this, Observer {response ->
            initializeRecyclerView(response)
        })

    }

    private fun initializeRecyclerView(weatherData: ForecastResponse) {

        id_forecast_list.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = WeatherAdapter(weatherData)
        }

    }
}