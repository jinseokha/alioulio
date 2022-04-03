package com.alio.ulio.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-03
 * @desc
 */

interface alioApi {

    @GET("dev/friend/insert")
    fun insertFriend(
        @Query("uuid") uuid : String,
        @Query("profileNickname") profileNickname : String,
        @Query("profileThumbnailImage") profileThumbnailImage : String
    ) : Single<ResponseBody>
}