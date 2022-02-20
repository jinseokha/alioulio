package com.alio.ulio.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-13
 * @desc
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = io.reactivex.rxjava3.disposables.CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}