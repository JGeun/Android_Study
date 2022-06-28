package com.example.retrofit.util

import android.content.Context
import android.widget.Toast

class Utils {
    companion object {
        fun makeToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        }
    }
}