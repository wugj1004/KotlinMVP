package com.wugj.mykotlin.entity

import mykotlin.base.BaseEntity
import java.util.ArrayList

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/5/20
 * </br>
 * version:
 */
class MoviesListEntity : BaseEntity() {

    var page: Int = 0
    var total_results: Int = 0
    var total_pages: Int = 0
    lateinit var results: ArrayList<MoviesEntity>

}
