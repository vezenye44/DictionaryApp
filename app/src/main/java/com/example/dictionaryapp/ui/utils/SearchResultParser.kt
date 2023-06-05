package com.example.dictionaryapp.ui.utils

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.local.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<Word> {
    val dataModel = ArrayList<Word>()
    if (list.isNotEmpty()) {
        for (entity in list) {
            dataModel.add(Word(entity.word, null))
        }
    }
    return dataModel
}

fun convertDataModelSuccessToEntity(data: List<Word>): HistoryEntity? {
    return if (data.isEmpty() || data[0].text.isNullOrEmpty())
        HistoryEntity(data[0].text!!, data[0].meanings?.get(0)?.translation.toString())
    else null
}