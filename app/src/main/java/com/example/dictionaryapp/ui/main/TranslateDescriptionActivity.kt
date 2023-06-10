package com.example.dictionaryapp.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.ImageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.utils.viewById
import com.example.networkstatus.INetworkStatus
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.get

class TranslateDescriptionActivity() : AppCompatActivity() {

    private val swipeRefreshLayout by viewById<SwipeRefreshLayout>(R.id.description_screen_swipe_refresh_layout)
    private val headerTextview by viewById<TextView>(R.id.description_header_textview)
    private val descriptionTextview by viewById<TextView>(R.id.description_textview)
    private val descriptionImageview by viewById<ImageView>(R.id.description_imageview)

    private val connection: INetworkStatus = get()

    private val word: String by lazy { intent.getStringExtra(WORD_EXTRA).orEmpty() }
    private val description: String by lazy { intent.getStringExtra(DESCRIPTION_EXTRA).orEmpty() }
    private val imageUrl: String? by lazy { intent.getStringExtra(URL_EXTRA) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate_description)

        setActionbarHomeButtonAsUp()

        swipeRefreshLayout.setOnRefreshListener {
            startLoadingOrShowError()
        }
        setData()
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startLoadingOrShowError() {
        if (connection.isOnline(applicationContext)) {
            setData()
        } else {
            AlertDialog.Builder(this).setTitle(R.string.dialog_title_device_is_offline)
                .setMessage(R.string.dialog_message_device_is_offline).show()
        }
        stopLoading()
    }


    private fun setData() {

        headerTextview.text = word
        descriptionTextview.text = description
        val url = imageUrl
        if (url != null) {
            imageLoading(descriptionImageview, url)
        } else {
            stopLoading()
        }

    }

    private fun imageLoading(imageView: ImageView, imageUrl: String) {
        useCoilToLoadPhoto(imageView, imageUrl)
    }

    private fun stopLoading() {
        swipeRefreshLayout.isRefreshing = false

    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String) {
        Picasso.get().load("https:$imageLink").placeholder(R.drawable.ic_no_photo_vector).fit()
            .centerCrop().into(imageView, object : Callback {
                override fun onSuccess() {
                    stopLoading()
                }

                override fun onError(e: Exception?) {
                    stopLoading()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    private fun useGlideToLoadPhoto(imageView: ImageView, imageLink: String) {
        Glide.with(imageView).load("https:$imageLink").listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                stopLoading()
                imageView.setImageResource(R.drawable.ic_load_error_vector)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                stopLoading()
                return false
            }
        }).apply(
            RequestOptions().placeholder(R.drawable.ic_no_photo_vector).centerCrop()
        ).into(imageView)
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        val request = ImageRequest.Builder(this).data("https:$imageLink").target(onStart = {
            imageView.setImageResource(R.drawable.ic_no_photo_vector)
        }, onSuccess = { result ->
            stopLoading()
            imageView.setImageDrawable(result)
        }, onError = {
            stopLoading()
            imageView.setImageResource(R.drawable.ic_load_error_vector)
        }).build()
        ImageLoader(this).enqueue(request)
    }

    companion object {

        val DIALOG_FRAGMENT_TAG = TranslateDescriptionActivity::class.simpleName

        private const val WORD_EXTRA = "WORD_EXTRA"
        private const val DESCRIPTION_EXTRA = "DESCRIPTION_EXTRA"
        private const val URL_EXTRA = "URL_EXTRA"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            imageUrl: String?,
        ): Intent = Intent(context, TranslateDescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, imageUrl)
        }
    }
}