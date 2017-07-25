package com.zhongke.launcher

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager

/**
 * Created by ${xingen} on 2017/7/20.
 * 悬浮窗口的工具类
 */
class SuspensionWindowManagerUtils {
    companion object {
         var windowManager: WindowManager?=null
         var layoutParams: WindowManager.LayoutParams?=null
         var suspensionWindowWidget: SuspensionWindowLayout? = null
        /**
         * 创建悬浮窗口
         */
        @JvmStatic
        fun createSuspensionWindow(context: Context) {
            if (suspensionWindowWidget==null){
                suspensionWindowWidget= SuspensionWindowLayout(context)
            }
            getWindowManager(context)!!.addView(suspensionWindowWidget, getWidgetLayoutParams())
        }
        /**
         * 移除悬浮窗口
         */
        fun removeSuspensionWindow(context: Context) {
            if (suspensionWindowWidget != null) {
                getWindowManager(context)!!.removeView(suspensionWindowWidget)
                suspensionWindowWidget = null
            }
        }

        /**
         * 悬浮窗口是否已经打开
         */
        fun windowIsOpen():Boolean{
            if (suspensionWindowWidget!=null)
                return true
            else  return false
        }


        /**
         * 获取悬浮窗口的布局参数
         */
        fun getWidgetLayoutParams(): WindowManager.LayoutParams? {
            if (layoutParams == null) {
                layoutParams = WindowManager.LayoutParams()
                layoutParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
                layoutParams!!.format = PixelFormat.RGBA_8888
                layoutParams!!.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                layoutParams!!.gravity = Gravity.LEFT or Gravity.TOP
                layoutParams!!.x = windowManager!!.defaultDisplay.width
                layoutParams!!.y =0
                layoutParams!!.width = SuspensionWindowLayout.widget_width
                layoutParams!!.height = SuspensionWindowLayout.widget_height
            }
            return layoutParams
        }

        /**
         * 获取窗口管理器
         */
        fun getWindowManager(context: Context): WindowManager ?{
            if (windowManager == null) {
                windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            }
            return windowManager
        }
    }
}