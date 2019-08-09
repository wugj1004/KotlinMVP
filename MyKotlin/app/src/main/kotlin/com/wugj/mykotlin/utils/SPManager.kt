package mykotlin.utils

import android.content.Context
import com.wugj.mykotlin.App

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/20
 * </br>
 * version:
 */

object SPManager{
    /** SharedPreferences 文件名 */
    private val SP_FILE_NAME = "KOTLIN_SHARED_PREF"

    /** SharedPreferences 对象 */
    private val sharedPref = App.instance.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)


    fun <T:Any> put(key: String, value: T) {
        when(value){
            is String -> sharedPref.edit().putString(key, value).apply()
            is Boolean -> sharedPref.edit().putBoolean(key, value).apply()
            is Int -> sharedPref.edit().putInt(key, value).apply()
            is Float -> sharedPref.edit().putFloat(key, value).apply()
            is Long -> sharedPref.edit().putLong(key, value).apply()
        }
    }

    fun <T:Any> get(key: String, value: T) : Any? {
        when(value){
            is String -> return sharedPref.getString(key, value)
            is Boolean -> return sharedPref.getBoolean(key, value)
            is Int -> return sharedPref.getInt(key, value)
            is Float -> return sharedPref.getFloat(key, value)
            is Long -> return sharedPref.getLong(key, value)
        }
        return null
    }



    fun getString(key: String, defValue: String) = sharedPref.getString(key, defValue)

    fun getInt(key: String, defValue: Int) = sharedPref.getInt(key, defValue)

    fun getBoolean(key: String, defValue: Boolean) = sharedPref.getBoolean(key, defValue)

    fun getLong(key: String, defValue: Long) = sharedPref.getLong(key, defValue)

    fun getFloat(key: String, defValue: Float) = sharedPref.getFloat(key, defValue)


    /**
     * 全部清除文件中的内容
     */
    fun clear() = sharedPref.edit().clear().apply()


    /**
     * 根据 key，从 SharedPref 中移除数据
     * @param key 键名
     */
    fun remove(key: String) = sharedPref.edit().remove(key).apply()


    /**
     * 获取 SharedPref 中所有数据集合
     * @return 保存的所有数据集合
     */
    fun getAll(): Map<String, *> = sharedPref.all
}