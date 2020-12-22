package com.liyulive.parksystem.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.liyulive.parksystem.ParkSystemApplication
import com.liyulive.parksystem.R
import com.liyulive.parksystem.logic.Repository
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.ui.cars.CarsAdapter
import com.liyulive.parksystem.ui.cars.CarsEditDialog
import com.liyulive.parksystem.ui.departure.DepartureViewModel
import com.liyulive.parksystem.ui.queue.QueueViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.concurrent.thread

class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: CarsAdapter
    private lateinit var queueViewModel: QueueViewModel

    lateinit var editDialog: CarsEditDialog

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        queueViewModel = ViewModelProvider(this).get(QueueViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        add_car.setOnClickListener {
            editDialog = CarsEditDialog(this.requireActivity(), "add")
            editDialog.show()
            editDialog.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.parkingList = Repository.queryParkingCars() as ArrayList<Cars>
        val layoutManager = LinearLayoutManager(ParkSystemApplication.context)
        parkingCars.layoutManager = layoutManager
        adapter = this.activity?.let { CarsAdapter(it, homeViewModel.parkingList) }!!
        parkingCars.adapter = adapter
        Toast.makeText(context, "已刷新，目前车库容量${homeViewModel.parkingList.size}/11", Toast.LENGTH_SHORT).show()
    }

}