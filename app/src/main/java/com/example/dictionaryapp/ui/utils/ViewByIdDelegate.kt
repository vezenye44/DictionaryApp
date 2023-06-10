package com.example.dictionaryapp.ui.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

/**
Делегат, который возвращает View и следит за тем, чтобы корневой View был
актуален
 */
class ViewByIdDelegate<out T : View>(
    private val rootGetter: () -> View?,
    private val viewId: Int,
) {
    // Ссылка на root
    private var rootRef: WeakReference<View>? = null

    // Ссылка на View
    private var viewRef: T? = null

    // Метод вызывается при каждом обращении к переменной
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        // Получаем root
        val currentRoot = rootGetter()
        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    // Failsafe, возвращать хотя бы последнюю View
                    return view
                }
                throw IllegalStateException(
                    "Cannot get View, there is no root yet "
                )
            }
            // Создаём View
            view = currentRoot.findViewById(viewId)
            // Сохраняем ссылку на View, чтобы не создавать её каждый раз
            // заново
            viewRef = view
            // Сохраняем ссылку на root, чтобы не искать его каждый раз заново
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) { "View with id \"$viewId\" not found in root" }
        // Возвращаем View в момент обращения к ней
        return view
    }
}

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}

fun <T : View> View.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    val viewGroup: ViewGroup? = this as? ViewGroup
    return if (viewGroup != null) ViewByIdDelegate({ this }, viewId)
    else throw IllegalStateException("View must be a ViewGroup!!!")
}

fun <T : View> RecyclerView.ViewHolder.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ itemView }, viewId)
}