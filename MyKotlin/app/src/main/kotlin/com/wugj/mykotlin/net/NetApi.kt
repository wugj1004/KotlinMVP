package com.wugj.mykotlin.net

import com.wugj.mykotlin.entity.LoginEntity
import io.reactivex.Observable
import retrofit2.http.*


interface NetApi{

    /**
     * form-post请求事例
     */
    @FormUrlEncoded
    @POST(UrlDefinition.POST_LOGIN)
    fun formPostRequest(@FieldMap map:MutableMap<String, String> ): Observable<LoginEntity>

    /**
     * get请求事例
     */
    @GET(UrlDefinition.POST_LOGIN)
    fun getRequest(@QueryMap map:MutableMap<String, String> ): Observable<LoginEntity>


    @POST(UrlDefinition.POST_LOGIN)
    fun login(@Body map:MutableMap<String, String> ): Observable<LoginEntity>



}