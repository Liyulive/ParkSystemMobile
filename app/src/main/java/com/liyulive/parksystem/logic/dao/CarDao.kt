package com.liyulive.parksystem.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars

@Dao
interface CarDao {

    @Insert
    fun insertCar(car: Cars): Long

    @Insert
    fun insertQueueCars(car: QueueCars): Long

    @Query("select * from QueueCars")
    fun queryQueueCar(): List<QueueCars>

    @Query("select * from Cars where levTime = :levTime")
    fun queryParkingCar(levTime: Long): List<Cars>

    @Query("select * from Cars where levTime != :levTime")
    fun queryLeaveTime(levTime: Long): List<Cars>

    @Query("select * from QueueCars order by arrTime")
    fun queryQueueCarsByLeastArrTime(): List<QueueCars>

    @Query("delete from Cars where arrTime = :arrTime")
    fun deleteByArrTime(arrTime: Long)

    @Query("delete from QueueCars where arrTime = :arrTime")
    fun deleteQueueByArrTime(arrTime: Long)

    @Query("update Cars set license = :license where arrTime = :arrTime")
    fun updateLicenseByArrTime(license: String, arrTime: Long)

    @Query("update Cars set color = :color where arrTime = :arrTime")
    fun updateColorByArrTime(color: String, arrTime: Long)

    @Query("update Cars set type = :type where arrTime = :arrTime")
    fun updateTypeByArrTime(type: Int, arrTime: Long)

    @Query("update Cars set levTime = :leaveTime where arrTime = :arrTime")
    fun updateLeaveTimeByArrTime(leaveTime: Long,arrTime: Long)


}