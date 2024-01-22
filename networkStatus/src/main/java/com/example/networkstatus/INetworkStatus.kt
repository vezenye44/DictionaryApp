package com.example.networkstatus

import androidx.lifecycle.LiveData

interface INetworkStatus {

    fun getNetworkStatusLiveData(): LiveData<Boolean>
    fun isOnline(): Boolean
}