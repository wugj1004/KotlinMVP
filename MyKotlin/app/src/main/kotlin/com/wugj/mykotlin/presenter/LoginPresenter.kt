package com.wugj.mykotlin.presenter

import com.orhanobut.logger.Logger
import com.wugj.mykotlin.entity.LoginEntity
import com.wugj.mykotlin.module.LoginModule
import com.wugj.mykotlin.net.OnNetFinishedListener
import mykotlin.base.BasePresenter
import mykotlin.base.BaseView
import javax.inject.Inject


class LoginPresenter @Inject constructor() : BasePresenter<LoginView, LoginModule>() {

    /**
     * 登录数据通知view
     */
    fun login(map:MutableMap<String,  String>){//username:String, password:String
        mView?.let {
            val dispose = mModule.login(map, object : OnNetFinishedListener<LoginEntity> {
                override fun onSuccess(entity: LoginEntity) {
                    // 请求成功，恢复显示状态
                    it.netFinished()
                    // 更新数据
                    it.notifyData(entity)
                }

                override fun onFailed(throwable: Throwable) {
                    // 请求失败，显示网络异常界面
                    it.netError()
                    // 打印日志
                    Logger.e(throwable, "Login")
                }
            })
            addDisposable(dispose)
        }
    }
}
interface LoginView:BaseView{

    fun notifyData(entity:LoginEntity)

}