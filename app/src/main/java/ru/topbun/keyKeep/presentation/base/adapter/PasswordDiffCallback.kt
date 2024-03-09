package ru.topbun.keyKeep.presentation.base.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.topbun.keyKeep.domain.enities.PasswordEntity

class PasswordDiffCallback: DiffUtil.ItemCallback<PasswordEntity>() {

    override fun areItemsTheSame(oldItem: PasswordEntity, newItem: PasswordEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PasswordEntity, newItem: PasswordEntity): Boolean {
        return oldItem == newItem
    }
}