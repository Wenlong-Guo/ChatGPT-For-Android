package io.github.guowenlong.chatgptforandroid.common.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: [RecyclerView.ViewHolder]带Databinding的基类
 * Author:      郭文龙
 * Date:        2023/4/3 0:50
 * Email:       guowenlong20000@sina.com
 */
class BaseBindingViewHolder<T : ViewDataBinding>(itemView: View, val itemDataBinding: T) :
    RecyclerView.ViewHolder(itemView)