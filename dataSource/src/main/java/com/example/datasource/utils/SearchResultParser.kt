package com.example.datasource.utils

import com.example.datasource.retrofit.WordDTO
import com.example.datasource.room.HistoryEntity
import com.example.model.models.Meanings
import com.example.model.models.Translation
import com.example.model.models.Word

fun mapWordDTOToResult(searchResult: List<WordDTO>): List<Word> {
    return searchResult.map { result ->
        var meanings: List<Meanings> = listOf()
        result.meanings?.let {
            meanings = it.map { meaningsDto ->
                Meanings(
                    Translation(meaningsDto.translation?.text ?: ""),
                    meaningsDto.imageUrl ?: ""
                )
            }
        }
        Word(result.text ?: "", meanings)
    }
}

fun mapHistoryEntityToResult(searchResult: List<HistoryEntity>): List<Word> {
    val dataModel = ArrayList<Word>()
    if (searchResult.isNotEmpty()) {
        for (entity in searchResult) {
            val translation = Translation(entity.description.orEmpty(), "")
            val meaning = Meanings(translation, "")
            dataModel.add(Word(entity.word, listOf(meaning)))
        }
    }
    return dataModel
}

fun convertDataModelToHistoryEntity(data: List<Word>): HistoryEntity? {
    return if (data.isNotEmpty())
        HistoryEntity(data[0].text, data[0].meanings[0].translation.text)
    else null
}
