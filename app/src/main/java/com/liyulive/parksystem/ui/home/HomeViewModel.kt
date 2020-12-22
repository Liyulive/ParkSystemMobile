package com.liyulive.parksystem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liyulive.parksystem.logic.model.Cars

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    var parkingList = ArrayList<Cars>()
    val text: LiveData<String> = _text
}