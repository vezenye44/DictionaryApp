package com.example.dictionaryapp.model.utils

import com.example.dictionaryapp.model.models.Meanings
import com.example.dictionaryapp.model.models.Translation
import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<Word> {
    val dataModel = ArrayList<Word>()
    if (list.isNotEmpty()) {
        for (entity in list) {
            val translation = Translation(entity.description.orEmpty(), null)
            val meaning = Meanings(translation, null)
            dataModel.add(Word(entity.word, listOf(meaning)))
        }
    }
    return dataModel
}

fun convertDataModelSuccessToEntity(data: List<Word>): HistoryEntity? {
    return if (data.isNotEmpty())
        HistoryEntity(data[0].text.orEmpty(), data[0].meanings?.get(0)?.translation?.text.orEmpty())
    else null
}


fun convertDataModelSuccessToEntityList(data: List<Word>): List<HistoryEntity>? {
    val entities = ArrayList<HistoryEntity>()
    return if (data.isNotEmpty())
        entities.apply {
            for (entity in data) {
                entities.add(
                    HistoryEntity(
                        entity.text.orEmpty(),
                        entity.meanings?.get(0)?.translation?.text.orEmpty()
                    )
                )
            }
        }
    else null
}