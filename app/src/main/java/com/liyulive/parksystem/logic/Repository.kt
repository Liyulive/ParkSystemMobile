package com.liyulive.parksystem.logic

import com.liyulive.parksystem.ParkSystemApplication
import com.liyulive.parksystem.logic.model.Cars
import com.liyulive.parksystem.logic.model.QueueCars

object Repository {

    val carDao = AppDatabase.getDatabase(ParkSystemApplication.context).carDao()

    fun addCar(car: Cars) {
        carDao.insertCar(car)
    }

    fun addQueue(car: QueueCars) {
        carDao.insertQueueCars(car)
    }

    fun queryAllCars(): List<QueueCars> {
        return carDao.queryQueueCar()
    }

    fun queryParkingCars(): List<Cars> {
        return carDao.queryParkingCar(0)
    }

    fun queryLeaveCars(): List<Cars> {
        return carDao.queryLeaveTime(0)
    }

    fun queryQueueCarsByLeastArrTime(): List<QueueCars> {
        return carDao.queryQueueCarsByLeastArrTime()
    }

    fun deleteByArrTime(arrTime: Long) {
        carDao.deleteByArrTime(arrTime)
    }

    fun deleteQueueByArrTime(arrTime: Long) {
        carDao.deleteQueueByArrTime(arrTime)
    }

    fun updateLicenseByArrTime(license: String, arrTime: Long) {
        carDao.updateLicenseByArrTime(license, arrTime)
    }

    fun updateColorByArrTime(color: String, arrTime: Long) {
        carDao.updateColorByArrTime(color, arrTime)
    }

    fun updateTypeByArrTime(type: Int, arrTime: Long) {
        carDao.updateTypeByArrTime(type, arrTime)
    }

    fun updateLeaveTimeByArrTime(leaveTime: Long, arrTime: Long) {
        carDao.updateLeaveTimeByArrTime(leaveTime, arrTime)
    }

}