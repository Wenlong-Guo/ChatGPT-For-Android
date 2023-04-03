package io.github.guowenlong.chatgptforandroid.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: RecyclerView 的 adapter 的基类
 * Author:      郭文龙
 * Date:        2023/4/3 0:51
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseBindingAdapter<T, BD : ViewDataBinding>(open var data: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<BaseBindingViewHolder<BD>>() {

    protected abstract val itemLayoutId: Int

    /**
     * 绑定holder的数据
     *
     * @param holder   子holder
     * @param position 索引
     * @param itemData 实体数据
     * @param binding item对应的binding
     */
    protected abstract fun bindData(
        holder: BaseBindingViewHolder<BD>,
        position: Int,
        itemData: T,
        binding: BD
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<BD> {
        val itemDataBinding = DataBindingUtil.inflate<BD>(
            LayoutInflater.from(parent.context),
            itemLayoutId,
            parent,
            false
        )
        return BaseBindingViewHolder(itemDataBinding.root, itemDataBinding)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<BD>, position: Int) {
        bindData(holder, position, data[position], holder.itemDataBinding)
        holder.itemDataBinding.executePendingBindings()
    }

    override fun getItemCount(): Int = data.size

}