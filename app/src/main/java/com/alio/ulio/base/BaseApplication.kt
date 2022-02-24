package com.alio.ulio.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-13
 * @desc
 */
open class BaseApplication : AppCompatActivity() {

    companion object {
        lateinit var instance : BaseApplication
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance= this

    }
}