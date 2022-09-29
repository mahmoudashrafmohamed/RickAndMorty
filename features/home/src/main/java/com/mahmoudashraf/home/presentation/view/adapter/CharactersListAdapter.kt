package com.mahmoudashraf.home.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmoudashraf.core.base.BaseAdapter
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.databinding.ItemCharacterBinding
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.local.entities.CharacterLocalEntity

class CharactersListAdapter : BaseAdapter<CharacterLocalEntity>() {

  private val diffCallback = object : DiffUtil.ItemCallback<CharacterLocalEntity>() {
    override fun areItemsTheSame(oldItem: CharacterLocalEntity, newItem: CharacterLocalEntity): Boolean {
      return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: CharacterLocalEntity, newItem: CharacterLocalEntity): Boolean {
      return oldItem.hashCode() == newItem.hashCode()
    }
  }

  override val differ = AsyncListDiffer(this, diffCallback)

  override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val binding = parent.viewBinding(ItemCharacterBinding::inflate)
    return CharacterViewHolder(binding)
  }

  inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root), Binder<CharacterLocalEntity> {
    override fun bind(item: CharacterLocalEntity) {
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
}
