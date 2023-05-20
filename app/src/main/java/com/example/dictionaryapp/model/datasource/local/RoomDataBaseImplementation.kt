package com.example.dictionaryapp.model.datasource.local

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImplementation : DataSource<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> {
        TODO("not implemented")
    }
}