package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(
    private val dataSource: DataSource<List<Word>>
) : Repository<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return dataSource.getData(word)
    }
}
