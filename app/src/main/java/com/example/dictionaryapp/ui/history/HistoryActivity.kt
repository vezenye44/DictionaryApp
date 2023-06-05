package com.example.dictionaryapp.ui.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityHistoryBinding
import com.example.dictionaryapp.model.models.AppState
import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.ui.base.BaseActivity
import com.example.dictionaryapp.ui.history.history_rv.HistoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityHistoryBinding
    private var adapter: HistoryAdapter? = null

    override val viewModel: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        viewModel.getData("", false)
    }

    override fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val dataModel = data.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        initRecyclerView(dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (data.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = data.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(data.error.message)
            }
        }
    }

    private fun initRecyclerView(dataModel: List<Word>) {
        binding.historyActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.historyActivityRecyclerview.adapter = HistoryAdapter(dataModel)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
    }

    private fun showViewSuccess() {
        binding.workingFrameLayout.visibility = View.VISIBLE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.workingFrameLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() {
        binding.workingFrameLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.VISIBLE
    }
}