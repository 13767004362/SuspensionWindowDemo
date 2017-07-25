package com.zhongke.launcher

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.PermissionChecker
import java.lang.Exception

/**
 * Created by ${xingen} on 2017/7/20.
 *
 * 权限工具类
 *
 */
class PermissionUtils{

    companion object {
        /**
         * 检查指定权限是否授权
         */
        @JvmStatic
        fun  checekPermission(context: Context,permissionNanme: String):Boolean{
            var checkResult = false
               if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                  if (getCurrentAppTargetVersion(context)>=Build.VERSION_CODES.M){
                     if ( context.checkSelfPermission(permissionNanme)==PackageManager.PERMISSION_GRANTED){
                         checkResult=true
                     }
                  }else{
                      checkResult=(PermissionChecker.checkSelfPermission(context,permissionNanme)==PackageManager.PERMISSION_GRANTED)
                  }
               }else{
                   checkResult=(context.packageManager.checkPermission(permissionNanme,context.packageName)==PackageManager.PERMISSION_GRANTED)
               }
            return checkResult
        }
        /**
         * 获取当前运用程序的默认target版本。
         */
        fun getCurrentAppTargetVersion(context: Context):Int{
            var targetVersion=0
            try {
                targetVersion= context.packageManager.getPackageInfo(context.packageName,0).applicationInfo.targetSdkVersion
            }catch (e:Exception){
                e.printStackTrace()
            }
            return targetVersion
        }

        @JvmField
        val SET_PREFERRED_APPLICATIONS= android.Manifest.permission.SYSTEM_ALERT_WINDOW
        @JvmField
        val SET_PREFERRED_APPLICATIONS_CODE=1
    }


}