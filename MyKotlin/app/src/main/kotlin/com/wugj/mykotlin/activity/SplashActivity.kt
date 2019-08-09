package com.wugj.mykotlin.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.TelephonyManager
import android.widget.Toast
import com.wugj.mykotlin.R
import com.wugj.mykotlin.presenter.BlankPresenter
import com.wugj.mykotlin.utils.permission.PermissionListener
import com.wugj.mykotlin.utils.permission.PermissionUtils
import mykotlin.base.BaseActivity
import java.util.ArrayList


/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/6/6
 * </br>
 * version:
 */
class SplashActivity: BaseActivity<BlankPresenter>() {

    val MY_REQUEST_CODE = 0x001

    lateinit var instance:SplashActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        instance = this

        val permissions = arrayOf(Manifest.permission.READ_PHONE_STATE)
        requestPermission(permissions, object :PermissionListener{
            override fun onGranted() {
                Toast.makeText(instance,"权限成功",Toast.LENGTH_LONG).show()
            }

            override fun onDenied() {
                Toast.makeText(instance,"权限失败",Toast.LENGTH_LONG).show()
            }

        })
    }

    //6.0 动态申领权限
    fun requestPermission(permissions: Array<String>, listener: PermissionListener) {

        //记录申请失败的权限
        val permissionList = ArrayList<String>()
        for (p in permissions) {
            val checkSelfPermission = ContextCompat.checkSelfPermission(instance, p)
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
                permissionList.add(p)
                listener.onDenied()
            }
        }
        //动态申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(instance, permissionList.toTypedArray(), MY_REQUEST_CODE)
        } else {
            listener.onGranted()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_REQUEST_CODE) {

            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                    //动态申请权限被拒，提示手动打开权限
                    PermissionUtils.permissionSet(instance, permissions[i])

                }
            }

        }
    }

    private fun startLogin(){
        val intent = Intent()
        intent.setClass(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun saveInfo(){

    }


}