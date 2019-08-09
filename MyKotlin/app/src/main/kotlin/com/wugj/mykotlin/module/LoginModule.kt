package com.wugj.mykotlin.module

import com.wugj.mykotlin.entity.LoginEntity
import com.wugj.mykotlin.net.OnNetFinishedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mykotlin.base.BaseModule
import javax.inject.Inject


class LoginModule @Inject constructor(): BaseModule() {

    /**
     * 登录接口调用数据
     */
    fun login(map:MutableMap<String,  String>, listener: OnNetFinishedListener<LoginEntity>): Disposable {
        return netClient
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onSuccess(it) }, { listener.onFailed(it) })
    }

}