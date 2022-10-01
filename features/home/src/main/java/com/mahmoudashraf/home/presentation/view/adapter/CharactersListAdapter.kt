package com.mahmoudashraf.home.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmoudashraf.core.base.BaseAdapter
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.databinding.ItemCharacterBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.databinding.ItemLoadingBinding
import com.mahmoudashraf.home.presentation.model.BaseCharacterUIModel
import com.mahmoudashraf.home.presentation.model.CharacterUIModel
import com.mahmoudashraf.home.presentation.model.LoadingItemUIModel

class CharactersListAdapter : BaseAdapter<BaseCharacterUIModel>() {

    private val diffCallback = object : DiffUtil.ItemCallback<BaseCharacterUIModel>() {
        override fun areItemsTheSame(
            oldItem: BaseCharacterUIModel,
            newItem: BaseCharacterUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BaseCharacterUIModel,
            newItem: BaseCharacterUIModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val characterItemBinding = parent.viewBinding(ItemCharacterBinding::inflate)
        val loadingBinding = parent.viewBinding(ItemLoadingBinding::inflate)
        return if (viewType==R.layout.item_character)CharacterViewHolder(characterItemBinding)
        else LoadingViewHolder(loadingBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is CharacterUIModel) R.layout.item_character else R.layout.item_loading
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<CharacterUIModel> {
        override fun bind(item: CharacterUIModel) {
            binding.apply {
                tvCharacterName.text = item.name
                Glide.with(imgCharacter).load(item.image).into(imgCharacter)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }

    class LoadingViewHolder( binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root),Binder<LoadingItemUIModel> {
        override fun bind(item: LoadingItemUIModel) = Unit
    }
}
