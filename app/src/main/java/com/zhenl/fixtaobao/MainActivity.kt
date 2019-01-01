package com.zhenl.fixtaobao

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import java.io.DataOutputStream

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var os: DataOutputStream? = null
        try {
            val process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process.outputStream)
            val commands = arrayOf("rm -rf /data/data/com.taobao.taobao/files/bundleBaseline/*",
                    "chmod 500 /data/data/com.taobao.taobao/files/bundleBaseline")
            for (command in commands) {
                os.write(command.toByteArray())
                os.writeBytes("\n")
                os.flush()
            }
            os.writeBytes("exit\n")
            os.flush()
            Toast.makeText(applicationContext, "已修复", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        finish()
    }
}
