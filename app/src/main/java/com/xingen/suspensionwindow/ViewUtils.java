package com.xingen.suspensionwindow;

import java.lang.reflect.Field;

/**
 * Created by ${xingen} on 2017/7/20.
 */

public class ViewUtils {


    /**
     *  反射获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(){
        int x=0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
             x = (Integer) field.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  x;
    }

}
