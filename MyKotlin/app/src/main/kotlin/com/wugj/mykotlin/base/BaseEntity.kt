package mykotlin.base

/**
 * description: 数据实体类基类
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/20
 * </br>
 * version:
 */
open class BaseEntity{
    /** 返回信息  */
    open var data: String? = null
    /** 返回码  */
    open var errorno: String? = null
    /** 返回码对应信息介绍  */
    open var errormsg: String? = null
    /** 层级  */
    open var total: Int = 0
}