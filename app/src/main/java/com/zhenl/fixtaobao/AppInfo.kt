package com.zhenl.fixtaobao

import android.graphics.drawable.Drawable
import java.io.Serializable

/**
 * Created by lin on 20-5-16.
 */
data class AppInfo(
    val title: String,
    val packageName: String,
    val sourceDir: String,
    @Transient val icon: Drawable
) : Serializable