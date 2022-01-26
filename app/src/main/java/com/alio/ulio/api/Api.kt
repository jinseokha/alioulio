package com.alio.ulio.api

import android.util.Log
import com.kakao.sdk.user.model.User

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-26
 * @desc
 */
object Api {
    const val KAKAK_BASE_URL = "https://k.kakaocdn.net"


    @JvmStatic
    fun getProfilePath(profile: User?) : String? {
        var pro = profile?.properties?.get("profileImageUrl")

        Log.d("test", "" + pro)

        return pro
        //return KAKAK_BASE_URL + profilePath
    }
}