package com.example.myuniversityclient.data.repository
import android.content.SharedPreferences

fun SharedPreferences.change(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}
