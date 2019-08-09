package com.wugj.mykotlin.utils.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类名称：PermissionUtils
 * 类描述：权限申请工具类
 * 创建人：ghl
 * 创建时间：2017/10/12 上午11:23
 * 修改人：ghl
 * 修改时间：2017/10/12 上午11:23
 *
 * @version v1.0
 */

public class PermissionUtils {

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 描述：检测是否开启通知
     */
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 描述：用户选择了不在提示按钮，或者系统默认不在提示（如MIUI）
     * "引导用户到应用设置页去手动授权,注意提示用户具体需要哪些权限】
     * "获取相关权限失败:xxxxxx,将导致部分功能无法正常使用，需要到设置页面手动授权"
     *
     * @Params: p为null时则提示通知权限，针对oppo、vivo等默认设置程序无通知权限的系统 非空时则是其他敏感权限
     * @Return:
     */
    public static void permissionSet(final Activity topActivity, String p) {
        if (p == null) {
            showPermissionDialog(topActivity, p);
        } else {
            //是否选择了不再提示
            boolean b = ActivityCompat.shouldShowRequestPermissionRationale(topActivity, p);
            if (!b) {
                switch (p) {
                    case "android.permission.WRITE_EXTERNAL_STORAGE":
                    case "android.permission.READ_EXTERNAL_STORAGE":
                        showPermissionDialog(topActivity, "存储");
                        break;
                    case "android.permission.READ_CONTACTS":
                    case "android.permission.WRITE_CONTACTS":
                    case "android.permission.GET_ACCOUNTS":
                        showPermissionDialog(topActivity, "通讯录");
                        break;
                    case "android.permission.READ_PHONE_STATE":
                    case "android.permission.CALL_PHONE":
                    case "android.permission.READ_CALL_LOG":
                    case "android.permission.WRITE_CALL_LOG":
                    case "com.android.voicemail.permission.ADD_VOICEMAIL":
                    case "android.permission.USE_SIP":
                    case "android.permission.PROCESS_OUTGOING_CALLS":
                        showPermissionDialog(topActivity, "电话");
                        break;
                    case "android.permission.READ_CALENDAR":
                    case "android.permission.WRITE_CALENDAR":
                        showPermissionDialog(topActivity, "日历");
                        break;
                    case "android.permission.CAMERA":
                        showPermissionDialog(topActivity, "相机");
                        break;
                    case "android.permission.ACCESS_FINE_LOCATION":
                    case "android.permission.ACCESS_COARSE_LOCATION":
                        showPermissionDialog(topActivity, "位置");
                        break;
                    case "android.permission.BODY_SENSORS":
                        showPermissionDialog(topActivity, "传感器");
                    case "android.permission.RECORD_AUDIO":
                        showPermissionDialog(topActivity, "录音");
                        break;
                    case "android.permission.MICROPHONE":
                        showPermissionDialog(topActivity, "麦克风");
                        break;
                    case "android.permission.SEND_SMS":
                    case "android.permission.RECEIVE_SMS":
                    case "android.permission.READ_SMS":
                    case "android.permission.RECEIVE_WAP_PUSH":
                    case "android.permission.RECEIVE_MMS":
                    case "android.permission.READ_CELL_BROADCASTS":
                        showPermissionDialog(topActivity, "短信");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 描述：用户手动设置权限弹框
     */
    private static void showPermissionDialog(final Activity topActivity, String p) {

        String message = "您选择了不授予好租" + p + "权限并选择了不再询问，这将导致程序无法正常使用，请打开设置界面，授予权限";
        if (p == null) {
            message = "您选择了不授予好租通知权限，这将导致程序更新无法正常使用，请打开设置界面，授予权限";
        }
        new AlertDialog.Builder(topActivity)
                .setMessage(message)
                .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户至设置页手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", topActivity.getApplicationContext().getPackageName(), null);
                        intent.setData(uri);
                        topActivity.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户手动授权，权限请求失败
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //引导用户手动授权，权限请求失败
            }
        }).show();
    }
}
