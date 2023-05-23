package com.example.dictionaryapp.model.datasource.local

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource

class RoomDataBaseImplementation : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        TODO("not implemented")
    }
}