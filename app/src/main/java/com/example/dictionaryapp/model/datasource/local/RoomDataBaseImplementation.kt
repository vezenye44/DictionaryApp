package com.example.dictionaryapp.model.datasource.local

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSourceLocal
import com.example.dictionaryapp.model.datasource.local.room.HistoryDao
import com.example.dictionaryapp.ui.utils.convertDataModelSuccessToEntity
import com.example.dictionaryapp.ui.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(
    private val historyDao: HistoryDao,
) : DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        val data = historyDao.all()
        return mapHistoryEntityToSearchResult(data)
    }

    override suspend fun saveToDB(entity: List<Word>) {
        convertDataModelSuccessToEntity(entity)?.let {
            historyDao.insert(it)
        }
    }
}