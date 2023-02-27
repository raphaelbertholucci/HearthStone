package com.bertholucci.home.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import coil.load
import com.bertholucci.home.R

fun ImageView.loadFromUrl(image: String?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.img_splash)
        error(R.drawable.img_splash)
    }
}

fun TextView.setValueIfNotEmpty(value: String) {
    text = value
    isGone = value.isEmpty()
}
