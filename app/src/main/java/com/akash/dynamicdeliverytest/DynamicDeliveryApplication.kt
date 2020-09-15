package com.akash.dynamicdeliverytest

import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication

/**
 * Created by Akash on 15/09/20
 */
class DynamicDeliveryApplication : SplitCompatApplication(){
    override fun onCreate() {
        super.onCreate()
        SplitCompat.install(this)
    }
}