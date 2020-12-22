package com.liyulive.parksystem.ui.cars

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.liyulive.parksystem.R
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars
import java.text.SimpleDateFormat
import java.util.*

class QueueCarsAdapter(val context: Context,val carsList: List<QueueCars>) : RecyclerView.Adapter<QueueCarsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val licence: TextView = view.findViewById(R.id.parking_license_text)
        val color: TextView = view.findViewById(R.id.parking_color_text)
        val parkingTime: TextView = view.findViewById(R.id.parking_time_text)
        val leaveTime: TextView = view.findViewById(R.id.leave_time_text)
        val type: TextView = view.findViewById(R.id.type_text)
        val card: MaterialCardView = view.findViewById(R.id.itemCard)
    }

    lateinit var editDialog: CarsEditDialog

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueCarsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.park_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QueueCarsAdapter.ViewHolder, position: Int) {
        val car = carsList[position]
        holder.licence.text = car.license
        holder.color.text = car.color
        holder.type.text = when (car.type) {
            0 -> "小车"
            1 -> "小卡"
            2 -> "中卡"
            3 -> "大卡"
            else -> "小车"
        }
        holder.parkingTime.text = "到达时间：" + millsToDate(car.arrTime)
        holder.leaveTime.visibility = View.GONE
    }

    override fun getItemCount() = carsList.size

    private fun millsToDate(mills: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val str = sdf.format(mills)
        return str
    }

}