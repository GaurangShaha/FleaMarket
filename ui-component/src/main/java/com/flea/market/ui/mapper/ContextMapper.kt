package com.flea.market.ui.mapper

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.toActivity(): Activity? {
    when (this) {
        is Activity -> return this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            return null
        }
    }
}
