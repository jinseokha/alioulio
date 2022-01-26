package com.alio.ulio.binding

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

import android.util.Log

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.security.MessageDigest


/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-26
 * @desc
 *
 * https://black-jin0427.tistory.com/178
 */

class MyTransformation (context: Context?, radius: Int, cornerType: CornerType?): BitmapTransformation() {

    enum class CornerType {
        NONE, ALL, TOP, BOTTOM
    }

    private var radius = 0
    private var cornerType: CornerType? = null



    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }

    /**
     *
     * @param bitmapPool
     * An interface for a pool that allows users to reuse Bitmap objects.
     *
     * @param original
     * Glide로 받아온 이미지
     *
     * @param width
     * ImageView의 넓이
     *
     * @param height
     * ImageView의 높이
     */
    override fun transform(
        bitmapPool: BitmapPool,
        original: Bitmap,
        width: Int,
        height: Int
    ): Bitmap? {
        Log.d("MyTag", "imageView 사이즈 width : $width , height : $height") // imageView 사이즈
        val orgWidth = original.width
        val orgHeight = original.height
        Log.d("MyTag", "받아온 이미지 사이즈 orgWidth : $orgWidth , orgHeight : $orgHeight") // 받아온 이미지의 사이즈
        val scaleX = width.toFloat() / orgWidth
        val scaleY = height.toFloat() / orgHeight
        val scaledWidth: Float
        val scaledHeight: Float
        if (orgWidth >= orgHeight) {
            scaledWidth = scaleY * orgWidth
            scaledHeight = height.toFloat()
        } else {
            scaledWidth = width.toFloat()
            scaledHeight = scaleX * orgHeight
        }
        Log.d("MyTag", "스케일 사이즈 scaledWidth : $scaledWidth , scaledHeight : $scaledHeight")
        var result: Bitmap = bitmapPool[width, height, Bitmap.Config.ARGB_8888]
        if (result == null) {
            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        //캔버스 준비
        val canvas = Canvas(result)

        //크레파스 준비
        val paint = Paint()
        paint.setAntiAlias(true)
        paint.setColor(-0xbdbdbe)
        when (cornerType) {
            CornerType.ALL -> {
                val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
                canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)

                //SRC_IN -> source 이미지가 destination 이미지를 덮습니다.
                paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            }
            CornerType.TOP -> {
                val rect = Rect(0, 0, width, height)
                val rectF = RectF(rect)
                canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)

                //Fill in bottom corner
                val bottomRect = Rect(0, height / 2, width, height)
                canvas.drawRect(bottomRect, paint)

                //SRC_IN -> source 이미지가 destination 이미지를 덮습니다.
                paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            }
            CornerType.BOTTOM -> {
                val rect = Rect(0, 0, width, height)
                val rectF = RectF(rect)
                canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)

                //Fill in top corner
                val topRect = Rect(0, 0, width, height / 2)
                canvas.drawRect(topRect, paint)

                //SRC_IN -> source 이미지가 destination 이미지를 덮습니다.
                paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            }
            else -> {}
        }

        //블랙펜서 비트맵(source) 그리기
        val targetRect = RectF(0f, 0f, scaledWidth, scaledHeight)
        canvas.drawBitmap(original, null, targetRect, paint)
        return result
    }

    /**
     * 다른 기능일 경우 ID 가 달라야 합니다.
     */
    fun getId(): String? {
        return "id : " + System.currentTimeMillis()
    }
}