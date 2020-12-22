package com.liyulive.parksystem.ui.departure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.liyulive.parksystem.ParkSystemApplication
import com.liyulive.parksystem.R
import com.liyulive.parksystem.logic.Repository
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.ui.cars.CarsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notifications.*

class DepartureFragment : Fragment() {

    private lateinit var departureViewModel: DepartureViewModel
    private lateinit var adapter: CarsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        departureViewModel =
                ViewModelProvider(this).get(DepartureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        departureViewModel.leaveCarsList = Repository.queryLeaveCars() as ArrayList<Cars>
        val layoutManager = LinearLayoutManager(ParkSystemApplication.context)
        leaveCars.layoutManager = layoutManager
        adapter = this.activity?.let { CarsAdapter(it, departureViewModel.leaveCarsList) }!!
        leaveCars.adapter = adapter
    }
}