package com.xingen.suspensionwindow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
            //未开启窗口，则开启
            if (!SuspensionWindowManagerUtils.windowIsOpen()) {
                SuspensionWindowManagerUtils.createSuspensionWindow(applicationContext)
            }
        }
    }
}
