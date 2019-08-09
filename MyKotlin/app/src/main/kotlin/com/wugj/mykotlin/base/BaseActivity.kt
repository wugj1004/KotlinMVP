package mykotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import mykotlin.utils.AppManager
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<*, *>>  : DaggerAppCompatActivity() , BaseView{


    open var Tag:String? = BaseActivity::class.simpleName

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: AppCompatActivity

    /** 当前界面 Presenter 对象 */
    @Inject protected lateinit var presenter: P

    protected lateinit var arrr :List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 保存当前 Context 对象
        mContext = this

        // 添加到 AppManager 应用管理
        AppManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 从应用管理移除当前 Activity 对象
        AppManager.removeActivity(this)

        // 界面销毁时，消费所有事件，清空引用
        presenter.dispose()
        presenter.detach()
    }


    override fun netError() {

    }

    override fun noData() {

    }

    override fun loading() {

    }

    override fun hideloading() {

    }

    override fun netFinished() {

    }
}
