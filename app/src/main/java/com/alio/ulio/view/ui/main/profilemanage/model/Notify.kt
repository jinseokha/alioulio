package com.alio.ulio.view.ui.main.profilemanage.model

import java.io.Serializable

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-09
 * @desc
 */

data class Notify(
    var title :String?,
    var isExpanded: Boolean = false) : Serializable {
}
