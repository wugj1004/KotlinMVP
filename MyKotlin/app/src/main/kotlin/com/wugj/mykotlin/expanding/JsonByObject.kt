package com.wugj.mykotlin.expanding

import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


/**
 * description: kotlin 中用 object 代替静态类；companion object代替静态方法
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/18
 * </br>
 * version:
 */
object JsonByObject{}


/**
 * 对象数组转换为你JSON数组
 * @param arrs
 * @param <T>
 * @return
 */
fun <T:Any>arrToJsonArr( arrs:Array<T>):JSONArray{
    val jsonArray = JSONArray()
    for (obj in arrs){
        jsonArray.put(objToJsonObj(obj))
    }
    return jsonArray
}


/**
 * 对象集合转换JSON数组
 * @param lists
 * @param <T>
 * @return
 */
fun <T:Any>listToJsonArr(arrs:List<T>):JSONArray{
    val jsonArray = JSONArray()
    for (obj in arrs){
        jsonArray.put(objToJsonObj(obj))
    }
    return jsonArray
}

/**
 * Object转换为JSONObject；
 * 通过java反射获取"某个类"对应的值；前提是"类"必须实现 Serializable
 * @param t
 * @param <T>
 * @return  JSONObject.toString 转换为 json格式的字符串（字符串中间不会穿插反斜杠）
 */
private fun <T:Any>objToJsonObj(t:T):JSONObject{
    val jsonObject = JSONObject()

    val props=t::class.memberProperties
    props.forEachIndexed { _, it ->
        it.isAccessible = true
        jsonObject.put(it.name,it.call(t))
    }

    return jsonObject
}