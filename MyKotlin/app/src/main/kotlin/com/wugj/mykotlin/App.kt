package com.wugj.mykotlin

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.wj.kotlintest.dagger.DaggerApplicationSub
import com.wugj.mykotlin.expanding.Envi
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication(){

    override fun applicationInjector() : AndroidInjector<out DaggerApplication> {
        return DaggerApplicationSub.builder().create(this) // 编译后生成
    }

    init {}

    override fun onCreate() {
        super.onCreate()
        instance = this

        // 初始化 Logger
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?) = BuildConfig.DEBUG
        })

        //初始化设备信息
        Envi.initialize(this)
    }


    companion object {
        lateinit var instance: App
    }
}