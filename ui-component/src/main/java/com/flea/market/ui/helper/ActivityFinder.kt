package com.flea.market.ui.helper

import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
public fun findActivity(): AppCompatActivity? {
    var context = LocalContext.current
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) return context
        context = context.baseContext
    }
    return null
}
