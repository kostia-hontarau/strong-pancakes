package com.strongpancakes.quest.utils

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import com.strongpancakes.quest.R

/**
 * Created by dima_korolev on 17/06/2017.
 */

fun Activity.start(activity: Class<out Activity>, vararg flags: Int = intArrayOf()) {
    val intent = Intent(this, activity)
    flags.forEach { intent.addFlags(it) }
    startActivity(intent)
}

fun Activity.showProgress() {
    var progressView = progressView
    if (progressView != null) {
        progressView.visibility = View.VISIBLE
    } else {
        progressView = layoutInflater.inflate(R.layout.item_progress_bar, null)
        (findViewById(android.R.id.content) as? ViewGroup)?.addView(progressView)
    }
}

fun Activity.hideProgress() {
    progressView?.visibility = View.INVISIBLE
}

fun Fragment.hideProgress() {
    activity?.progressView?.visibility = View.INVISIBLE
}

fun Fragment.showProgress() {
    activity?.showProgress()
}

val Activity.progressView: View?
get() {
    val rootContainer = (findViewById(android.R.id.content) as ViewGroup)
    if (rootContainer.childCount == 0) return null
    val topView = rootContainer.getChildAt(rootContainer.childCount - 1)
    return if (topView.id == R.id.progressViewScreen) topView else null
}

var SharedPreferences.isFirstLaunch: Boolean
get() = this.getBoolean("isFirstLaunch", true)
set(value) {
    this.edit().apply {
        putBoolean("isFirstLaunch", value)
        commit()
    }
}