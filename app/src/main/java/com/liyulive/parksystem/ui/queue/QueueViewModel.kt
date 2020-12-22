package com.liyulive.parksystem.ui.queue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars

class QueueViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    lateinit var queueCarsList: ArrayList<QueueCars>

}