package com.example.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.model.models.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View<T> {

    abstract val viewModel: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.subscribe().observe(this) {
            renderData(it)
        }
    }

    abstract override fun renderData(data: T)
}