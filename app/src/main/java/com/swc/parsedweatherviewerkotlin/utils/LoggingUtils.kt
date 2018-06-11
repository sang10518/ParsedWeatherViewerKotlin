package com.swc.parsedweatherviewerkotlin.utils

import android.util.Log
import com.swc.parsedweatherviewerkotlin.BuildConfig

/**
 * Created by sangwonc on 2018. 6. 11..
 */

object LoggingUtils {

    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }
}
