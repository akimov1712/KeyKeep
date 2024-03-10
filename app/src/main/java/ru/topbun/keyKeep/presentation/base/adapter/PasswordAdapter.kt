package ru.topbun.keyKeep.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.ItemPasswordBinding
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.utils.getFaviconUrl

class PasswordAdapter: ListAdapter<PasswordEntity, PasswordAdapter.PasswordViewHolder>(
    PasswordDiffCallback()
) {

    var setOnItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPasswordBinding.inflate(inflater, parent, false)
        return PasswordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            tvName.text = item.name
            tvEmail.text = item.email
            Picasso.get().load(getFaviconUrl(item.site)).error(R.drawable.ic_favicon_not_found).into(ivFavicon)
        }
        holder.itemView.setOnClickListener {
            setOnItemClickListener?.invoke(item.id)
        }
    }

    inner class PasswordViewHolder(val binding: ItemPasswordBinding): RecyclerView.ViewHolder(binding.root)
}