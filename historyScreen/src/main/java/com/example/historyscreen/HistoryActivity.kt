package com.example.historyscreen

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseActivity
import com.example.historyscreen.databinding.ActivityHistoryBinding
import com.example.historyscreen.history_rv.HistoryAdapter
import com.example.model.models.AppState
import com.example.model.models.Word
import org.koin.android.scope.createScope

class HistoryActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityHistoryBinding
    private var adapter: HistoryAdapter? = null

    override val viewModel: HistoryViewModel by createScope().inject()

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
                    binding.progressBarHorizontal.progress = data.progress!!
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