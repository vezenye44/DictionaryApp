package com.example.dictionaryapp.ui.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseActivity
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.ui.main.translates_rv.TranslatesAdapter
import com.example.historyscreen.HistoryActivity
import com.example.model.models.AppState
import com.example.model.models.Word
import com.example.networkstatus.INetworkStatus
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.createScope


class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: TranslatesAdapter? = null

    private val onListItemClickListener: TranslatesAdapter.OnListItemClickListener by lazy {
        object : TranslatesAdapter.OnListItemClickListener {
            override fun onItemClick(data: Word) {
                startActivity(
                    TranslateDescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text,
                        data.meanings.first().translation.text,
                        data.meanings.first().imageUrl,
                    )
                )
            }
        }
    }

    override val viewModel: TranslateViewModel by createScope().inject()
    private val networkStatus: INetworkStatus = getKoin().get()

    private var searchDialogFragment: SearchDialogFragment? = null
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setHighApiSplashScreen()

        initView()
    }

    private fun initView() {

        networkStatus.apply {
            updateNetworkStatus(isOnline())
            getNetworkStatusLiveData().observe(this@MainActivity) {
                updateNetworkStatus(it)
            }
        }

        binding.searchFab.setOnClickListener {
            searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment?.setOnSearchClickListener { searchWord ->
                viewModel.getData(searchWord, networkStatus.isOnline())
            }
            searchDialogFragment?.show(
                supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
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
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    // TODO : Изменить названия методов
    private fun initRecyclerView(dataModel: List<Word>) {
        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter =
            TranslatesAdapter(onListItemClickListener, dataModel)
    }

    private fun setHighApiSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenHideAnimation()
            setSplashScreenDuration()
        }
    }

    @RequiresApi(31)
    private fun setSplashScreenHideAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideLeft = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideLeft.interpolator = AnticipateInterpolator()
            slideLeft.duration = 1000L
            slideLeft.doOnEnd { splashScreenView.remove() }
            slideLeft.start()
        }
    }

    private fun setSplashScreenDuration() {
        var isHideSplashScreen = false
        object : CountDownTimer(3000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun updateNetworkStatus(isOnline: Boolean) {
        if (isOnline) {
            snackbar?.dismiss()
            showIsOnlineScreen()
        } else {
            showIsOfflineScreen()
            snackbar = Snackbar.make(
                binding.root,
                "Проверьте подключение к сети",
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar?.show()
        }
    }

    private fun showIsOfflineScreen() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.searchFab.visibility = GONE

        searchDialogFragment?.dismiss()

        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showIsOnlineScreen() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.searchFab.visibility = VISIBLE

        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}