package com.pizza.app.utils

import android.content.Context
import android.widget.Toast

class ToastHelper {

    companion object {
        var toast: Toast? = null
    }

    fun show(context: Context, text: String) {
        toast?.cancel()
        toast= Toast.makeText(context, text, Toast.LENGTH_LONG)
        toast?.show()
    }
}