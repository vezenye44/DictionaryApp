package com.example.datasource.datasource.remote

import com.example.model.models.Word
import com.example.datasource.datasource.base.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
) : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> =
        remoteProvider.getData(word)
}