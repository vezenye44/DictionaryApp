package com.example.datasource.datasource.remote

import com.example.datasource.datasource.base.DataSource
import com.example.datasource.retrofit.ApiService
import com.example.datasource.retrofit.BaseInterceptor
import com.example.datasource.utils.mapWordDTOToResult
import com.example.model.models.Word
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return mapWordDTOToResult(getService(BaseInterceptor.interceptor).search(word))
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