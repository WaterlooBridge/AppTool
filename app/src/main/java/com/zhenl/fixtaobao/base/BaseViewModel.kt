package com.zhenl.fixtaobao.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhenl.fixtaobao.events.ViewEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by lin on 20-5-16.
 */
abstract class BaseViewModel : ViewModel() {

    private val _viewEvents = MutableLiveData<ViewEvent>()
    val viewEvents: LiveData<ViewEvent> get() = _viewEvents

    internal fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}