package com.wugj.mykotlin.utils.permission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：ActivityCollector
 * 类描述：Activity的管理类
 * 创建人：ghl
 * 创建时间：2017/9/19 上午11:50
 * 修改人：ghl
 * 修改时间：2017/9/19 上午11:50
 *
 * @version v1.0
 */
public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加当前Activity入栈
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除当前Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 获取栈顶activity
     *
     * @return
     */
    public static Activity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        }
        return activityList.get(activityList.size() - 1);
    }

}
