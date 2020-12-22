package com.liyulive.parksystem.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cars(
    var license: String,
    var color: String,
    var type: Int,
    var arrTime: Long,
    var levTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}