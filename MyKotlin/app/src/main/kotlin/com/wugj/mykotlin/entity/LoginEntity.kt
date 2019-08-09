package com.wugj.mykotlin.entity

import mykotlin.base.BaseEntity
import java.util.ArrayList

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/6/5
 * </br>
 * version:
 */
class LoginEntity : BaseEntity() {

    var user_id: String? = null
    var hr_id: String? = null
    var user_name: String? = null
    var user_name_english: String? = null
    var account: String? = null
    var group_name: String? = null
    var phone_number: String? = null
    var dept_type: String? = null
    var token: String? = null
    var city_id: String? = null
    var domain_name: String? = null
    var system_env: String? = null

    lateinit var latlng: ArrayList<LatlngEntity>



}