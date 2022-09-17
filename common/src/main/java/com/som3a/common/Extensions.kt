package com.som3a.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
fun Context.haveNetworkConnection(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        activeNetwork?.isConnected == true
    }
}

fun Context.getCapturedImage(): CapturedImage {
    var name = ""
    val capturedImage = CapturedImage()
    var photoURI: Uri? = null
    try {
        if (TextUtils.isEmpty(name)) {
            val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            name = "weather_" + sdf.format(Date())
        }
        val storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val photoFile = File.createTempFile(
            name ?: "",  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        capturedImage.absPath = photoFile.absolutePath
        photoURI = FileProvider.getUriForFile(
            this,
            "com.som3a.android.android.fileprovider",
            photoFile
        )
    } catch (e: java.lang.Exception) {
        // Error occurred while creating the File
        e.printStackTrace()
    }
    capturedImage.uri = photoURI
    return capturedImage
}

fun Int.toDp(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density + 0.5).toInt()

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.setIsVisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun Button.state(isUsable: Boolean) {
    isEnabled = isUsable
    isClickable = isUsable
    isActivated = isUsable
    alpha = if (isUsable) 1f else 0.5f
}