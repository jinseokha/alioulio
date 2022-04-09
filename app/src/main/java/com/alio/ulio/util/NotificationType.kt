package com.alio.ulio.util

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-07
 * @desc
 */

enum class NotificationType(val title: String, val id: Int) {
    NORMAL("일반 알림", 0),
    EXPANDABLE("확장형 알림", 1),
    CUSTOM("커스텀 알림", 3),
}