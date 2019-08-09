package com.wugj.mykotlin.net

import com.wugj.mykotlin.BuildConfig

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/20
 * </br>
 * version:
 */
object UrlDefinition{


    /** 正式版本服务器地址 */
    private val API_RELEASE = "mob.haozu.com"
    /** 测试版本服务器地址 */
    private val API_DEBUG = "mob.haozu.com"
    /** 实际使用服务器地址 */
    private val API_DOMAIN = if (BuildConfig.DEBUG) API_DEBUG else API_RELEASE

    /** 标记-是否使用加密协议 */
    private val USE_SSL = false
    /** 请求方案 */
    private val SCHEME = if (USE_SSL) "https://" else "http://"

    /** 实际使用完整服务器地址 */
    val BASE_URL = SCHEME + API_DOMAIN


    /** 登录接口 **/
    const val POST_LOGIN = "auth/nlogin/"
}

