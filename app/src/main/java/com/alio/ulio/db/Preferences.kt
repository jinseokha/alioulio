package com.alio.ulio.db

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kakao.sdk.user.model.User

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-26
 * @desc
 */
object Preferences {
    private lateinit var preferences : SharedPreferences

    private const val PROFILE = "profile"

    fun init(context : Context) {
        preferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

    var profile : User
    get() {
        var gson = Gson()
        var json = preferences.getString(PROFILE, "")
        var obj: User = gson.fromJson(json, User::class.java)
        return obj
    }
    set(value) {
        var gson = Gson()
        var json = gson.toJson(value)
        preferences.edit().putString(PROFILE, json).commit()
    }

}