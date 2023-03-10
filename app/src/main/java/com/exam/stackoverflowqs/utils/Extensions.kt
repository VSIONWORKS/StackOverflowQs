package com.exam.stackoverflowqs.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View

fun View.show(show:Boolean) {
    if (show) this.visibility = View.VISIBLE
    else  this.visibility = View.GONE
}

fun String.browseExternal(context: Context) {
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(this)
    )
    context.startActivity(browserIntent)
}