package com.zhenl.fixtaobao

import android.app.Application
import androidx.lifecycle.MutableLiveData

/**
 * Created by lin on 20-5-16.
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
        val globalLoading = MutableLiveData<Boolean>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}