package com.wugj.mykotlin

import android.os.Bundle
import com.wugj.mykotlin.presenter.BlankPresenter
import mykotlin.base.BaseActivity

class MainActivity : BaseActivity<BlankPresenter>() {


    override var Tag: String? = MainActivity::class.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onStart() {
        super.onStart()

    }


    override fun onDestroy() {
        super.onDestroy()
    }



}
