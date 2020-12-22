package com.liyulive.parksystem.ui.cars

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.liyulive.parksystem.R
import com.liyulive.parksystem.logic.Repository
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars
import kotlinx.android.synthetic.main.edit_dialog.*
import kotlin.concurrent.thread

class CarsEditDialog : Dialog {

    var type = ""
    var licence = ""
    var carType = 0
    var corlor = ""
    var arrTime: Long = 0
    var carList = ArrayList<Cars>()
    var queueList = ArrayList<QueueCars>()

    constructor(context: Context, themeResId: Int) : super(context, R.style.CustomDialog) {
        setContentView(R.layout.edit_dialog)
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    constructor(context: Context, string: String) : this(context, 0) {
        type = string
    }

    constructor(context: Context, string: String, incolor: String, inlicence: String, incarType: Int, inarrTime: Long) : this(context, 0) {
        type = string
        corlor = incolor
        licence = inlicence
        carType = incarType
        arrTime = inarrTime
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (type == "edit") {
            edit_licence.setText(licence)
            edit_color.setText(corlor)
            spinner.setSelection(carType)
            saveBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.GONE
            outBtn.visibility = View.VISIBLE
            addBtn.visibility = View.GONE
        }
        if (type == "add") {
            saveBtn.visibility = View.GONE
            deleteBtn.visibility = View.GONE
            addBtn.visibility = View.VISIBLE
            outBtn.visibility = View.GONE
        }
        if (type == "history") {
            edit_licence.setText(licence)
            edit_color.setText(corlor)
            spinner.setSelection(carType)
            saveBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
            addBtn.visibility = View.GONE
            outBtn.visibility = View.GONE
        }
        carList.clear()
        thread {
            carList = Repository.queryParkingCars() as ArrayList<Cars>
            queueList = Repository.queryAllCars() as ArrayList<QueueCars>
        }

        edit_licence.addTextChangedListener {
            carList.forEach {
                if (edit_licence.text.toString() == it.license) {
                    edit_licence_layout.error = "已存在相同车牌"
                    addBtn.isClickable = false
                    saveBtn.isClickable = false
                    return@forEach
                } else {
                    edit_licence_layout.error = null
                    addBtn.isClickable = true
                    saveBtn.isClickable = true
                }
            }
        }
    }

    public fun setOnClickListener(listener: View.OnClickListener) {
        deleteBtn?.setOnClickListener {
            thread {
                Repository.deleteByArrTime(arrTime)
            }.join()
            Toast.makeText(context, "已删除该条目", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        saveBtn?.setOnClickListener {
            thread {
                Repository.updateLicenseByArrTime(edit_licence.text.toString(), arrTime)
                Repository.updateColorByArrTime(edit_color.text.toString(), arrTime)
                Repository.updateTypeByArrTime(spinner.selectedItemPosition, arrTime)
            }.join()
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        addBtn?.setOnClickListener {
            val car = Cars(edit_licence.text.toString(), edit_color.text.toString(), spinner.selectedItemPosition, System.currentTimeMillis(), 0)
            if (queueList.size == 0 && carList.size < 11) {
                thread {
                    Repository.addCar(car)
                }.join()
                Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
            } else {
                thread {
                    Repository.addQueue(QueueCars(edit_licence.text.toString(), edit_color.text.toString(), spinner.selectedItemPosition, System.currentTimeMillis()))
                }
                Toast.makeText(context, "车库已满，正在排队中", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
        outBtn?.setOnClickListener {
            thread {
                Repository.updateLeaveTimeByArrTime(System.currentTimeMillis(), arrTime)
            }.join()
            Toast.makeText(context, "已出库", Toast.LENGTH_SHORT).show()
            if (queueList.size > 0) {
                var queueCarLicense = ""
                thread {
                    val queueCar = Repository.queryQueueCarsByLeastArrTime()
                    queueCarLicense = queueCar[0].license
                    val car = Cars(queueCar[0].license, queueCar[0].color, queueCar[0].type, System.currentTimeMillis(), 0)
                    Repository.addCar(car)
                    Repository.deleteQueueByArrTime(queueCar[0].arrTime)
                }.join()
                Toast.makeText(context, "${queueCarLicense}已排队入库", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
    }

}