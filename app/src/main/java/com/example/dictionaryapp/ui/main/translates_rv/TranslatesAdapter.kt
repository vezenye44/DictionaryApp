package com.example.dictionaryapp.ui.main.translates_rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.model.models.Word

class TranslatesAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<Word>,
) : RecyclerView.Adapter<TranslateViewHolder>() {

    fun setData(data: List<Word>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TranslateViewHolder.create(parent, onListItemClickListener::onItemClick)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TranslateViewHolder, position: Int) =
        holder.bind(data[position])


    interface OnListItemClickListener {
        fun onItemClick(data: Word)
    }
}