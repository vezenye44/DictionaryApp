package com.example.dictionaryapp.ui.main.translates_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.databinding.TranslatesListItemLayoutBinding
import com.example.dictionaryapp.model.data.Word

class TranslateViewHolder(
    itemView: View,
    private val listener: (Word) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    private val binding: TranslatesListItemLayoutBinding =
        TranslatesListItemLayoutBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup, itemClickListener: (Word) -> Unit): TranslateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TranslatesListItemLayoutBinding.inflate(inflater, parent, false)
            return TranslateViewHolder(binding.root, itemClickListener)
        }
    }

    fun bind(data: Word) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            binding.headerTextviewRecyclerItem.text = data.text
            binding.descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.text
            binding.root.setOnClickListener { listener(data) }
        }
    }

}