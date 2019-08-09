package mykotlin.base

/**
 * description: MVP View 基类
 */
interface BaseView{

    /**
     * 网络故障
     */
    fun netError()

    /**
     * 无数据
     */
    fun noData()

    /**
     * 加载中
     */
    fun loading()

    /***
     * 隐藏Dialog
     */
    fun hideloading()

    /**
     * 网络请求结束
     */
    fun netFinished()


}
