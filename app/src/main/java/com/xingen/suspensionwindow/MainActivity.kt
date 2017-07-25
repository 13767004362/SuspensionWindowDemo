package com.xingen.suspensionwindow

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.zhongke.launcher.SuspensionWindowManagerUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //点击开启悬浮窗口
        main_open_window.setOnClickListener {
                if (checkPermission()){
                    openSuspensionWindow()
                }else{
                    requestPermission()
                }
        }
    }

    /**
     * 开启悬浮弹窗
     */
    fun openSuspensionWindow(){
        //未开启窗口，则开启
        if (!SuspensionWindowManagerUtils.windowIsOpen()) {
            SuspensionWindowManagerUtils.createSuspensionWindow(applicationContext)
        }
    }

    /**
     * 申请权限的状态code
     */
    var request_code=1
    /**
     * 开启权限管理界面，授权。
     */
    fun requestPermission(){
        var intent=Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        startActivityForResult(intent,request_code)
    }

    /**
     * 当目标版本大于23时候，检查权限
     */
    fun checkPermission():Boolean{
        if (Build.VERSION.SDK_INT>=23)
            return Settings.canDrawOverlays(this)
        else
            return true
    }

    /**
     * 回调申请结果
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            request_code -> {
                if (checkPermission()) {  //用户授权成功
                    openSuspensionWindow()
                } else { //用户拒绝授权
                    Toast.makeText(application, "弹窗权限被拒绝", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
