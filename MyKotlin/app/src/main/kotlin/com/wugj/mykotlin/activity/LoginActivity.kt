package com.wugj.mykotlin.activity

import android.os.Bundle
import android.widget.Toast
import com.wugj.mykotlin.R
import com.wugj.mykotlin.entity.LoginEntity
import com.wugj.mykotlin.presenter.LoginPresenter
import com.wugj.mykotlin.presenter.LoginView
import mykotlin.base.BaseActivity


class LoginActivity: BaseActivity<LoginPresenter>(), LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 绑定 MVP
        presenter.attach(this)

    }

    override fun onResume() {
        super.onResume()

        val map : MutableMap<String,String> = HashMap()

        map["username"] = "wugaojie@haozu.com"
        map["password"] = "Wgj123456"
        presenter.login(map)
    }


    override fun notifyData(entity: LoginEntity) {

        Toast.makeText(this,"请求接口返回",Toast.LENGTH_LONG).show();
    }

}