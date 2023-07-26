package com.githubnames.presentation.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.githubnames.data.entities.User
import com.githubnames.databinding.ItemUserBinding

class UserAdapter :
    PagingDataAdapter<User, UserAdapter.UserViewHolder>(diffCallback = diffCallBack) {

    inner class UserViewHolder(internal val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val CROSS_FADE_ENABLED = true
        private const val ONE_SECOND = 1000
        val diffCallBack = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            userText.text = item?.login
            userAvatar.load(item?.avatar_url) {
                crossfade(CROSS_FADE_ENABLED)
                crossfade(ONE_SECOND)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
