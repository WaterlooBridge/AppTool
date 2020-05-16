package com.zhenl.fixtaobao

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhenl.fixtaobao.adapter.AppInfoAdapter
import com.zhenl.fixtaobao.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var viewModel: MainVM
    private val adapter: AppInfoAdapter by lazy { AppInfoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initVM()
        MyApplication.globalLoading.postValue(true)
        viewModel.loadAppList()
    }

    private fun initView() {
        rv?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            adapter.setOnItemClickListener { adapter, view, position ->
                val item = adapter.getItem(position) as AppInfo
                showMenu(item)
            }
        }
    }

    private fun initVM() {
        viewModel = getViewModel(MainVM::class.java)
        viewModel.appList.observe(this, Observer {
            MyApplication.globalLoading.postValue(false)
            adapter.setNewInstance(it)
        })
    }

    private fun showMenu(info: AppInfo) {
        val items =
            arrayOf("Open App", "Clear Odex")

        val builder =
            AlertDialog.Builder(this)
        builder.setItems(
            items
        ) { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            when (which) {
                0 -> AppController.openApp(info)
                1 -> AppController.clearOdex(info)
            }
        }
        builder.create().show()
    }
}
