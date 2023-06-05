package com.example.dictionaryapp.ui.history.history_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.databinding.HistoryListItemBinding
import com.example.dictionaryapp.model.models.Word

class HistoryViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val binding: HistoryListItemBinding =
        HistoryListItemBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): HistoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HistoryListItemBinding.inflate(inflater, parent, false)
            return HistoryViewHolder(binding.root)
        }
    }

    fun bind(data: Word) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            binding.headerHistoryTextviewRecyclerItem.text = data.text
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context, "on click: ${data.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}