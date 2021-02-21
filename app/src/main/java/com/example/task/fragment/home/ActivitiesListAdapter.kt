package com.example.task.fragment.home

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.domian.utils.dateDayFormat
import com.example.entity.localmodels.ActivityItem
import com.example.task.R
import com.example.task.base.adapter.BaseAdapter
import com.example.task.base.adapter.BaseViewHolder
import com.example.task.databinding.ItemActivityBinding

class ActivitiesListAdapter() :
    BaseAdapter<ViewBinding, ActivityItem, BaseViewHolder<ActivityItem, ViewBinding>>(
        EventsDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ActivityItem, ViewBinding> {
        return ItemViewHolder(
            ItemActivityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ItemViewHolder(private val binding: ItemActivityBinding) :
        BaseViewHolder<ActivityItem, ViewBinding>(binding) {
        override fun bind(item: ActivityItem) {
            with(binding) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    message.text = Html.fromHtml(item.message, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    message.text = Html.fromHtml(item.message)
                }
                time.text = item.timestamp.dateDayFormat()
                itemView.context?.let {
                    Glide.with(it)
                        .load(item.userData?.avatarUrl)
                        .circleCrop()
                        .placeholder(ContextCompat.getDrawable(it, R.drawable.ic_error_icon))
                        .into(userImage)
                }

                price.text =
                    String.format(itemView.context.resources.getString(R.string.price), item.amount)
            }
        }

    }
}

internal class EventsDiffCallback : DiffUtil.ItemCallback<ActivityItem>() {
    override fun areItemsTheSame(oldItem: ActivityItem, newItem: ActivityItem): Boolean =
        oldItem.userId == newItem.userId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ActivityItem, newItem: ActivityItem): Boolean =
        oldItem == newItem

}

