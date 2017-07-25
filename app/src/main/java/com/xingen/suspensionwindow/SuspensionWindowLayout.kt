package com.zhongke.launcher

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import com.xingen.suspensionwindow.R
import com.xingen.suspensionwindow.ViewUtils

import kotlinx.android.synthetic.main.item_message.view.*

/**
 * Created by ${xingen} on 2017/7/20.
 *
 * 悬浮窗口的布局，可以拖动。
 */
class SuspensionWindowLayout(context: Context) : RelativeLayout(context) {

    /**
     * statusbar系统状态栏的高度
     */
    var statusbarHeight = 0
    /**
     * 窗口管理器
     */
    val windowManager: WindowManager

    init {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var childView= View.inflate(context, R.layout.item_message,this)
        widget_width = childView.suspension_window_layout.layoutParams.width
        widget_height = childView.suspension_window_layout.layoutParams.height
    }

    /**
     *    按下屏幕时手指在x,y轴上的坐标
     */
    var down_x = 0.0f
    var down_y = down_x
    /**
     *    移动时候的手指在x,y轴上的坐标
     */
    var move_x = down_x
    var move_y = down_x
    /**
     * 按下屏幕时候，控件在x，y轴位置
     */
    var widget_x = down_x
    var widget_y = down_x

    /**
     * 重写处理拖动事件
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                widget_x = event.x
                widget_y = event.x
                //没有移动，down->up，点击事件
                down_x = event.rawX
                down_y = event.rawY - getStatusBarHeight()
                move_x = event.rawX
                move_y = event.rawY - getStatusBarHeight()
            }
            MotionEvent.ACTION_MOVE -> {
                // 手指移动的时候更新小悬浮窗的位置
                move_x = event.rawX
                move_y = event.rawY - getStatusBarHeight()
                updateWidgetPostion()
            }
            MotionEvent.ACTION_UP -> {//
                //坐标没有改变，是点击动作
                if (move_x == down_x && move_y == down_y) {
                         SuspensionWindowManagerUtils.removeSuspensionWindow(context)
                }
            }
            else -> {
            }
        }
        return true
    }

    /**
     * 更新控件位置,在x，y轴的的位置
     */
    fun updateWidgetPostion() {
        var layoutParams = SuspensionWindowManagerUtils.getWidgetLayoutParams()
        layoutParams!!.x = (move_x - widget_x).toInt()
        layoutParams!!.y = (move_y - widget_y).toInt()
        windowManager.updateViewLayout(this, layoutParams)
    }

    /**
     * 获取系统状态栏，返回状态栏高度的像素值
     */
    fun getStatusBarHeight(): Int {
        if (statusbarHeight == 0) {
            statusbarHeight = resources.getDimensionPixelSize(ViewUtils.getStatusBarHeight())
        }
        return statusbarHeight
    }

    companion object {
        var widget_width = 0
        var widget_height = 0
    }
}