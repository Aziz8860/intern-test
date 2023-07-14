package com.ariqbelajar.interntest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ariqbelajar.interntest.databinding.ItemUserBinding
import com.ariqbelajar.interntest.network.DataItem
import com.bumptech.glide.Glide

class UserListAdapter(private val clickListener: (DataItem) -> Unit) : PagingDataAdapter<DataItem, UserListAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, clickListener)
        }
    }


    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem, clickListener: (DataItem) -> Unit) {

            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivProfile)

            val fullName = "${data.firstName} ${data.lastName}"
            binding.tvName.text = fullName
            binding.tvEmail.text = data.email

            itemView.setOnClickListener {
                clickListener(data)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}