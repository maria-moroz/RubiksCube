package com.example.rubiksart

import android.animation.Animator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start = findViewById<LottieAnimationView>(R.id.start)
        val intent = Intent(this, ImageActivity::class.java)
        start.setOnClickListener{
            start.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(intent)
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationStart(animation: Animator?) {
                }
            })
            start.setMinAndMaxProgress(0.0f, 0.5f)
            start.playAnimation()
        }
    }

    override fun onRestart() {
        super.onRestart()
        val start = findViewById<LottieAnimationView>(R.id.start)
        start.progress = 0f
    }
}




/*var result: Array<Array<String?>> = arrayOf(
    arrayOf("B","B","B"),
    arrayOf("R","R","Y"),
    arrayOf("O","O","Y"))*/

/*var result: Array<Array<String?>> = arrayOf(
            arrayOf("B","R","Y"),
            arrayOf("R","R","Y"),
            arrayOf("G","G","Y"))*/

/*var result: Array<Array<String?>> = arrayOf(
    arrayOf("W","G","Y"),
    arrayOf("W","G","Y"),
    arrayOf("W","R","R"))*/

/*var result: Array<Array<String?>> = arrayOf(
    arrayOf("R","R","Y"),
    arrayOf("R","R","Y"),
    arrayOf("B","B","B"))*/

/*var result: Array<Array<String?>> = arrayOf(
    arrayOf("B","R","Y"),
    arrayOf("R","R","Y"),
    arrayOf("B","G","Y"))*/