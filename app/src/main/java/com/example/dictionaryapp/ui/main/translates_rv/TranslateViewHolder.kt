package com.example.dictionaryapp.ui.main.translates_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.utils.viewById
import com.example.model.models.Word

class TranslateViewHolder(
    itemView: View,
    private val listener: (Word) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    // View.viewById()
    private val headerTextviewRecyclerItem by viewById<TextView>(R.id.header_textview_recycler_item)

    // ViewHolder.viewById()
    private val descriptionTextviewRecyclerItem by
    itemView.viewById<TextView>(R.id.description_textview_recycler_item)

    companion object {
        fun create(parent: ViewGroup, itemClickListener: (Word) -> Unit): TranslateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.translates_list_item_layout, parent, false)

            return TranslateViewHolder(view, itemClickListener)
        }
    }

    fun bind(data: Word) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            headerTextviewRecyclerItem.text = data.text
            descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.text
            itemView.setOnClickListener { listener(data) }
        }
    }

}