package com.zhenl.fixtaobao.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.zhenl.fixtaobao.AppInfo
import com.zhenl.fixtaobao.R
import com.zhenl.fixtaobao.databinding.ItemPackageBinding

/**
 * Created by lin on 20-5-16.
 */
class AppInfoAdapter: BaseQuickAdapter<AppInfo, BaseDataBindingHolder<ItemPackageBinding>>(R.layout.item_package) {

    override fun convert(holder: BaseDataBindingHolder<ItemPackageBinding>, item: AppInfo) {
        holder.dataBinding?.item = item
    }
}