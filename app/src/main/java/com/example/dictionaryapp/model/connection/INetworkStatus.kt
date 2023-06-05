package com.example.dictionaryapp.model.connection

import android.content.Context

interface INetworkStatus {

    fun isOnline(context: Context): Boolean
}