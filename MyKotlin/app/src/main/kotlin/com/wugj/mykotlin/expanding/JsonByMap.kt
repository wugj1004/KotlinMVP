package com.wugj.mykotlin.expanding

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException
/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/20
 * </br>
 * version:
 */
object JsonByMap{}

/**
 *
 * @param mapList 转换 JSONArray
 * @param <V>
 * @return JSONArray.toString 转换为 json格式的字符串（字符串中间不会穿插反斜杠）
 */
fun <V>createJsonArray(mapList:List<Map<String, V>>): JSONArray {

    val jsonArray = JSONArray()
    for (map in mapList){
        jsonArray.put(createJsonObject(map))
    }
    return jsonArray
}

/**
 *
 * @param map 转换 JSONObject
 * @param <V>
 * @return JSONObject.toString 转换为 json格式的字符串（字符串中间不会穿插反斜杠）
 */
fun <V> createJsonObject(map: Map<String, V>): JSONObject {
    val jsonObject = JSONObject()
    for (key in map.keys) {
        try {
            jsonObject.put(key, map[key])
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
    return jsonObject
}