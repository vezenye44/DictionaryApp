package com.example.dictionaryapp.model.datasource.remote

import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.datasource.base.DataSource
import com.example.dictionaryapp.retrofit.ApiService
import com.example.dictionaryapp.retrofit.BaseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return getService(BaseInterceptor.interceptor).search(word)
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS =
            "https://dictionary.skyeng.ru/api/public/v1/"
    }
}