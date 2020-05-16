package com.zhenl.fixtaobao

import android.content.ComponentName
import android.content.Intent
import java.io.DataOutputStream
import java.io.File

/**
 * Created by lin on 20-5-16.
 */
object AppController {

    fun openApp(info: AppInfo) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.setPackage(info.packageName)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            val resolveInfo =
                MyApplication.instance.packageManager.queryIntentActivities(intent, 0)[0]
            intent.component = ComponentName(
                resolveInfo.activityInfo.applicationInfo.packageName,
                resolveInfo.activityInfo.name
            )
            MyApplication.instance.startActivity(intent)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun clearOdex(info: AppInfo) {
        val dir = File(info.sourceDir).parent
        execShell(
            arrayOf(
                "rm -rf $dir/oat/arm/base.odex",
                "rm -rf $dir/oat/arm64/base.odex",
                "am force-stop ${info.packageName}"
            )
        )
    }

    private fun execShell(commands: Array<String>) {
        var os: DataOutputStream? = null
        try {
            val process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process.outputStream)
            for (command in commands) {
                os.write(command.toByteArray())
                os.writeBytes("\n")
                os.flush()
            }
            os.writeBytes("exit\n")
            os.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}