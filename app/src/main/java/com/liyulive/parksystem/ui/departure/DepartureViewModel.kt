package com.liyulive.parksystem.ui.departure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liyulive.parksystem.logic.model.Cars

class DepartureViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    lateinit var leaveCarsList: ArrayList<Cars>
}