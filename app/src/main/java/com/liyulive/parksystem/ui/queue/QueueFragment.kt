package com.liyulive.parksystem.ui.queue

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
import com.liyulive.parksystem.logic.model.QueueCars
import com.liyulive.parksystem.ui.cars.CarsAdapter
import com.liyulive.parksystem.ui.cars.QueueCarsAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_notifications.*

class QueueFragment : Fragment() {

    private lateinit var queueViewModel: QueueViewModel
    private lateinit var adapter: QueueCarsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        queueViewModel =
                ViewModelProvider(this).get(QueueViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        queueViewModel.queueCarsList = Repository.queryAllCars() as ArrayList<QueueCars>
        val layoutManager = LinearLayoutManager(ParkSystemApplication.context)
        queueCars.layoutManager = layoutManager
        adapter = this.activity?.let { QueueCarsAdapter(it, queueViewModel.queueCarsList) }!!
        queueCars.adapter = adapter
    }

}