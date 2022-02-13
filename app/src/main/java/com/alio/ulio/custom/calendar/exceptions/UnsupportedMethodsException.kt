package com.alio.ulio.custom.calendar.exceptions

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

data class UnsupportedMethodsException(override val message: String) : RuntimeException(message)