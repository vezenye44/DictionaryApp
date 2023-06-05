package com.example.dictionaryapp.ui.history.history_rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.model.models.Word

class HistoryAdapter(
    private var data: List<Word> = arrayListOf()
) : RecyclerView.Adapter<HistoryViewHolder>() {


    fun setData(data: List<Word>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
