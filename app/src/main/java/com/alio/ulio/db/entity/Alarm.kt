package com.alio.ulio.db.entity

import java.io.Serializable

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-02-24
 * @desc
 */
data class Alarm(
    var hour: String?,
    var minute:String?) : Serializable {

}
