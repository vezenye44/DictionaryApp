package com.example.networkstatus

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AndroidNetworkStatus(context: Context) : INetworkStatus {

    private val networkStatusLiveData = MutableLiveData<Boolean>()

    private val availableNetworks = mutableSetOf<Network>()

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val request = NetworkRequest.Builder().build()
    private val callback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableNetworks.add(network)
            update(availableNetworks.isNotEmpty())
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            availableNetworks.remove(network)
            update(availableNetworks.isNotEmpty())
        }
    }

    init {
        connectivityManager.registerNetworkCallback(request, callback)
    }

    private fun update(isOnline: Boolean) {
        if (isOnline != networkStatusLiveData.value) {
            networkStatusLiveData.postValue(isOnline)
        }
    }

    override fun getNetworkStatusLiveData(): LiveData<Boolean> {
        return networkStatusLiveData
    }

    override fun isOnline(): Boolean {
        return networkStatusLiveData.value ?: false
    }
}