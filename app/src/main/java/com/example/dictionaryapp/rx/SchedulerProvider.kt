package com.example.dictionaryapp.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProvider : ISchedulerProvider {

    override fun io() = Schedulers.io()
    override fun ui() = AndroidSchedulers.mainThread()!!
}