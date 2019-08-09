package com.wugj.mykotlin.presenter

import mykotlin.base.BaseModule
import mykotlin.base.BasePresenter
import mykotlin.base.BaseView
import javax.inject.Inject

/**
 * 空白 Presenter，继承 [BaseMVPPresenter]
 */
class BlankPresenter @Inject constructor() : BasePresenter<BaseView, BaseModule>()