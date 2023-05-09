package com.example.dictionaryapp.rx

import io.reactivex.rxjava3.core.Scheduler

interface ISchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}