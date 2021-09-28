package com.example.rubiksart

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rubiksart.program.BitmapHelper
import io.uuddlrlrba.closepixelate.Pixelate.fromBitmap
import io.uuddlrlrba.closepixelate.PixelateLayer
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.properties.Delegates


class PixelationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pixelation)
        val pixel_image = findViewById<ImageView>(R.id.pixel_image)
        val ready = findViewById<Button>(R.id.ready)

        ready.setOnClickListener {
            val intent = Intent(this, PiecesActivity::class.java)
            startActivity(intent)
        }

        val pixelated: Bitmap = fromBitmap(
            BitmapHelper.instance.bitmap!!,
            PixelateLayer.Builder(PixelateLayer.Shape.Square)
                .setResolution(5f)
                .build()
        )
        //var bitmap = BitmapDrawable(resources, pixelated)

        /*var bitmap = Bitmap.createBitmap(pixelated.width, pixelated.height, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.BLUE)
        bitmap.setPixel(0, 0, Color.RED)
        val pixels = IntArray(pixelated.width * pixelated.height)*/

        //pixelated.getPixels(pixels, 0, 0, 0, 0, pixelated.width, pixelated.height)
        //val color = pixelated.getPixel(0,0)
        //val p = color.blue
        //ready.text = p.toString()
        //pixelated.setPixel(25,25, Color.RED)
        val finalImage = processingBitmap(pixelated)
        pixel_image?.setImageBitmap(finalImage)
        //pixel_image.setImageBitmap(BitmapHelper.instance.bitmap)
    }
    private fun processingBitmap(src: Bitmap): Bitmap? {
        val dest = Bitmap.createBitmap(
            src.width, src.height, src.config)
        val red = intArrayOf(255, 0, 0)
        val green = intArrayOf(0, 255, 0)
        val blue = intArrayOf(0, 0, 255)
        val yellow = intArrayOf(255, 255, 0)
        val orange = intArrayOf(255, 153, 0)
        val white = intArrayOf(255, 255, 255)
        var distance = IntArray(6)
        val pixelColor = IntArray(3)
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // получим каждый пиксель
                val pixel = src.getPixel(x, y)
                // получим информацию о прозрачности
                //val pixelAlpha = Color.alpha(pixelColor)
                // получим цвет каждого пиксель
                pixelColor[0] = Color.red(pixel)
                pixelColor[1] = Color.green(pixel)
                pixelColor[2] = Color.blue(pixel)

                distance[0] = findDistance(pixelColor, red)
                distance[1] = findDistance(pixelColor, green)
                distance[2] = findDistance(pixelColor, blue)
                distance[3] = findDistance(pixelColor, yellow)
                distance[4] = findDistance(pixelColor, orange)
                distance[5] = findDistance(pixelColor, white)

                var indexOfMin = 0
                for (i in 0..5) {
                    if (distance[i] <= distance[indexOfMin]) {
                        indexOfMin = i
                    }
                }
                // перемешаем цвета
                var newPixel by Delegates.notNull<Int>()
                when (indexOfMin) {
                    0 -> dest.setPixel(x, y, Color.rgb(red[0], red[1], red[2])) // red
                    1 -> dest.setPixel(x, y, Color.rgb(green[0], green[1], green[2]))// green
                    2 -> dest.setPixel(x, y, Color.rgb(blue[0], blue[1], blue[2])) // blue
                    3 -> dest.setPixel(x, y, Color.rgb(yellow[0], yellow[1], yellow[2])) // yellow
                    4 -> dest.setPixel(x, y, Color.rgb(orange[0], orange[1], orange[2])) // orange
                    5 -> dest.setPixel(x, y, Color.rgb(white[0], white[1], white[2])) // white
                }
                // полученный результат вернём в Bitmap
                //dest.setPixel(x, y, newPixel)
            }
        }
        return dest
    }

    fun findDistance(pixel: IntArray, color: IntArray): Int {
        return abs(pixel[0] - color[0]) + abs(pixel[1] - color[1]) + abs(pixel[2] - color[2])
        //return sqrt(pow((pixel[0] - color[0]).toDouble(), 2.0) + pow((pixel[1] - color[1]).toDouble(), 2.0) + pow((pixel[3] - color[3]).toDouble(), 2.0)).toInt()
    }


    /*private fun processingBitmap(src: Bitmap): Bitmap? {
        val dest = Bitmap.createBitmap(
            src.width, src.height, src.config)
        val red = intArrayOf(255, 0, 0)
        val green = intArrayOf(0, 255, 0)
        val blue = intArrayOf(0, 0, 255)
        val yellow = intArrayOf(255, 255, 0)
        val orange = intArrayOf(255, 153, 0)
        val white = intArrayOf(255, 255, 255)
        var distance = IntArray(6)
        val pixelColor = IntArray(3)
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // получим каждый пиксель
                val pixel = src.getPixel(x, y)
                // получим информацию о прозрачности
                //val pixelAlpha = Color.alpha(pixelColor)
                // получим цвет каждого пиксель
                pixelColor[0] = Color.red(pixel)
                pixelColor[1] = Color.green(pixel)
                pixelColor[2] = Color.blue(pixel)

                distance[0] = findDistance(pixelColor, red)
                distance[1] = findDistance(pixelColor, green)
                distance[2] = findDistance(pixelColor, blue)
                distance[3] = findDistance(pixelColor, yellow)
                distance[4] = findDistance(pixelColor, orange)
                distance[5] = findDistance(pixelColor, white)

                var indexOfMin = 0
                for (i in 0..5) {
                    if (distance[i] < distance[indexOfMin]) {
                        indexOfMin = i
                    }
                }
                // перемешаем цвета
                var newPixel by Delegates.notNull<Int>()
                when (indexOfMin) {
                    0 -> dest.setPixel(x, y, Color.rgb(red[0], red[1], red[2])) // red
                    1 -> dest.setPixel(x, y, Color.rgb(green[0], green[1], green[2]))// green
                    2 -> dest.setPixel(x, y, Color.rgb(blue[0], blue[1], blue[2])) // blue
                    3 -> dest.setPixel(x, y, Color.rgb(yellow[0], yellow[1], yellow[2])) // yellow
                    4 -> dest.setPixel(x, y, Color.rgb(orange[0], orange[1], orange[2])) // orange
                    5 -> dest.setPixel(x, y, Color.rgb(white[0], white[1], white[2])) // white
                }
                // полученный результат вернём в Bitmap
                //dest.setPixel(x, y, newPixel)
            }
        }
        return dest
    }

    fun findDistance(pixel: IntArray, color: IntArray): Int {
        return abs(pixel[0] - color[0]) + abs(pixel[1] - color[1]) + abs(pixel[2] - color[2])
    }*/
}
