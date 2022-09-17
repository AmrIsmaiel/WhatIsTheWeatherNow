package com.som3a.common

import android.net.Uri
import java.io.Serializable

data class CapturedImage(
    var uri: Uri? = null,
    var absPath: String? = null
) : Serializable