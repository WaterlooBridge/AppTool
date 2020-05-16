package com.zhenl.fixtaobao

import android.content.pm.PackageInfo
import com.zhenl.fixtaobao.base.BaseRepo

/**
 * Created by lin on 20-5-16.
 */
class MainRepo : BaseRepo() {

    suspend fun loadAppList(): MutableList<AppInfo> = launch {
        val context = MyApplication.instance
        val apps: ArrayList<AppInfo> = ArrayList()
        val installedPackages: List<PackageInfo> =
            MyApplication.instance.packageManager.getInstalledPackages(0)
        for (installedPackage in installedPackages) {
            val app = AppInfo(
                installedPackage.applicationInfo.loadLabel(context.packageManager).toString(),
                installedPackage.packageName,
                installedPackage.applicationInfo.sourceDir,
                installedPackage.applicationInfo.loadIcon(context.packageManager)
            )
            if ("com.tencent.mm" == app.packageName)
                apps.add(0, app)
            else
                apps.add(app)
        }
        apps
    }
}