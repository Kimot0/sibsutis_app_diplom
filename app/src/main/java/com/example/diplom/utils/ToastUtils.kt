package com.example.diplom.utils

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}