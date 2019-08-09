package mykotlin.base

import com.wugj.mykotlin.net.NetApi
import javax.inject.Inject


open class BaseModule @Inject constructor() {

    @Inject lateinit var netClient: NetApi
}