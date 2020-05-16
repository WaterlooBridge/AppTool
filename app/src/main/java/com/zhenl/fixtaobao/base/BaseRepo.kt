package com.zhenl.fixtaobao.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by lin on 20-5-16.
 */
abstract class BaseRepo {

    protected suspend fun <T> launch(block: () -> T): T = withContext(Dispatchers.IO) {
        block()
    }
}