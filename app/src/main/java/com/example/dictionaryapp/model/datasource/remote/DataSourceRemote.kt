package com.example.dictionaryapp.model.datasource.remote

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> =
        remoteProvider.getData(word)
}