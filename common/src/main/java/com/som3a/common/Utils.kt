package com.som3a.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

object Utils {

    @JvmStatic
    fun loadImageFromStorage(path: String): Bitmap? {
        try {
            val f = File(path)
            return BitmapFactory.decodeStream(FileInputStream(f))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}