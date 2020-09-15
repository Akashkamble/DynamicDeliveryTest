package com.akash.dynamicdeliverytest

import android.content.Context
import android.widget.Toast

/**
 * Created by Akash on 15/09/20
 */

fun Context.showLongToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}