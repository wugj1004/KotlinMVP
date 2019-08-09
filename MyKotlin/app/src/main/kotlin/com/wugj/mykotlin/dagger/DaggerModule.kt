package com.wugj.mykotlin.dagger

import com.orhanobut.logger.Logger
import com.wugj.mykotlin.BuildConfig
import com.wugj.mykotlin.MainActivity
import com.wugj.mykotlin.activity.LoginActivity
import com.wugj.mykotlin.activity.SplashActivity
import com.wugj.mykotlin.net.*
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Activity Dagger2 Module
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}

/**
 * SupportFragment Dagger2 Module
 */
@Module
abstract class SupportFragmentModule {
//    @ContributesAndroidInjector
//    abstract fun contributeMoviesListFragment(): MoviesListFragment
}

/**
 * 网络模块依赖注入
 */
@Module
class NetModule {
    @Provides
    @Singleton
    fun netClient(): NetApi {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addNetworkInterceptor(HeaderInterceptor())
                .addInterceptor(ParamsInterceptor(BuildConfig.DEBUG))
                .addInterceptor(LogInterceptor(
                        if (BuildConfig.DEBUG) Level.BODY else Level.NONE,
                        object : InterceptorLogger {
                            override fun log(msg: String) {
                                Logger.d(msg)
                            }
                        }))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(UrlDefinition.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(NetApi::class.java)
    }
}