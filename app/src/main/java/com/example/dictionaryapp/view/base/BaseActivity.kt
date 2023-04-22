package com.example.dictionaryapp.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.presenter.Presenter

abstract class BaseActivity<T: AppState>: AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    // Override
    protected abstract fun createPresenter(): Presenter<T, View>
    abstract override fun renderData(data: AppState)

    // Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}