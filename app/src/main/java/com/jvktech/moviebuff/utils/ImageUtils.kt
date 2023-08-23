package com.jvktech.moviebuff.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.nio.ByteBuffer

data class Roi(
    val left: Float,
    val top: Float,
    val width: Float,
    val height: Float
)


fun Bitmap.getRoi(roi: Roi): Bitmap {
    return Bitmap.createBitmap(
        this,
        (roi.left * width).toInt(),
        (roi.top * height).toInt(),
        (roi.width * width).toInt(),
        (roi.height * height).toInt()
    )
}

fun Bitmap.rotate(degrees: Float): Bitmap {
    return Bitmap.createBitmap(
        this,
        0,
        0,
        width,
        height,
        android.graphics.Matrix().apply { postRotate(degrees) },
        true
    )
}

