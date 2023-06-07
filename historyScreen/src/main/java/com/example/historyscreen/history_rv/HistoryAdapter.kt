package com.example.historyscreen.history_rv

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.models.Word

class HistoryAdapter(
    private var data: List<Word> = arrayListOf()
) : RecyclerView.Adapter<HistoryViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
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
