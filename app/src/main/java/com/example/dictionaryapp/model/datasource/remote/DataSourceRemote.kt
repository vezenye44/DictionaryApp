package com.example.dictionaryapp.model.datasource.remote

import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.datasource.base.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
) : DataSource<List<Word>> {
    suspend override fun getData(word: String): List<Word> =
        remoteProvider.getData(word)
}