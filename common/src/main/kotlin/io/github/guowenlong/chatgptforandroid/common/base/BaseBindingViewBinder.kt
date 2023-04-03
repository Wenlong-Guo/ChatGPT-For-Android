package io.github.guowenlong.chatgptforandroid.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.drakeet.multitype.ItemViewBinder

/**
 * Description: [ItemViewBinder]带Databinding的基类
 * Author:      郭文龙
 * Date:        2023/4/3 0:50
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseBindingViewBinder<T, BD : ViewDataBinding> :
    ItemViewBinder<T, BaseBindingViewHolder<BD>>() {

    protected abstract val itemLayoutId: Int

    /**
     * 绑定holder的数据
     *
     * @param holder   子holder
     * @param binding item对应的 data binding
     * @param itemData 实体数据
     */
    protected abstract fun bindData(
        holder: BaseBindingViewHolder<BD>,
        itemData: T,
        binding: BD
    )

    override fun onBindViewHolder(holder: BaseBindingViewHolder<BD>, item: T) {
        bindData(holder, item, holder.itemDataBinding)
        holder.itemDataBinding.executePendingBindings()
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseBindingViewHolder<BD> {
        val itemDataBinding = DataBindingUtil.inflate<BD>(
            LayoutInflater.from(parent.context),
            itemLayoutId,
            parent,
            false
        )
        return BaseBindingViewHolder(itemDataBinding.root, itemDataBinding)
    }
}