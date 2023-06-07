package com.example.datasource.datasource.local

import com.example.datasource.datasource.base.DataSourceLocal
import com.example.datasource.room.HistoryDao
import com.example.datasource.utils.convertDataModelSuccessToEntity
import com.example.datasource.utils.mapHistoryEntityToSearchResult
import com.example.model.models.Word

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