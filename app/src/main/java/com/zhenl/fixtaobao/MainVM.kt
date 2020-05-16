package com.zhenl.fixtaobao

import androidx.lifecycle.MutableLiveData
import com.zhenl.fixtaobao.base.BaseViewModel

/**
 * Created by lin on 20-5-16.
 */
class MainVM : BaseViewModel() {

    val mRepo = MainRepo()

    val appList: MutableLiveData<MutableList<AppInfo>> by lazy { MutableLiveData<MutableList<AppInfo>>() }

    fun loadAppList() {
        launch {
            appList.value = mRepo.loadAppList()
        }
    }
}