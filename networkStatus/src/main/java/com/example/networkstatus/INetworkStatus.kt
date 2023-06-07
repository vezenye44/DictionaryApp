package com.example.networkstatus

import android.content.Context

interface INetworkStatus {

    fun isOnline(context: Context): Boolean
}