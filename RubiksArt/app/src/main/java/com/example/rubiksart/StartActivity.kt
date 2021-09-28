package com.example.rubiksart

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.rubiksart.program.Answer
import java.io.Serializable

class StartActivity : AppCompatActivity() {
    var picture: Answer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        picture = intent.getSerializableExtra("picture") as Answer?
        val play = findViewById<LottieAnimationView>(R.id.play)
        showStart(picture)

        val intent = Intent(this, ActionActivity::class.java)
        val bundle = Bundle()
        play.setOnClickListener{
            play.playAnimation()
            play.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    bundle.putSerializable("picture", picture as Serializable)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }
    }
    fun showStart(picture: Answer?){
        var cellId = 1
        var cell = findViewById<ImageView>(R.id.cell11)
        var rotations = findViewById<TextView>(R.id.rotations)
        var top = findViewById<ImageView>(R.id.top)
        var front = findViewById<ImageView>(R.id.front)
        rotations.text = "Rotations: "+(picture!!.action.size-1).toString()
        for (i in 0..2) {
            for (j in 0..2) {
                when (cellId) {
                    1 -> cell = findViewById(R.id.cell11)
                    2 -> cell = findViewById(R.id.cell12)
                    3 -> cell = findViewById(R.id.cell13)
                    4 -> cell = findViewById(R.id.cell21)
                    5 -> cell = findViewById(R.id.cell22)
                    6 -> cell = findViewById(R.id.cell23)
                    7 -> cell = findViewById(R.id.cell31)
                    8 -> cell = findViewById(R.id.cell32)
                    9 -> cell = findViewById(R.id.cell33)
                }
                when (picture.answer[picture.answer.size-1][i][j]) {
                    "R" -> cell.setBackgroundResource(R.drawable.red)
                    "B" -> cell.setBackgroundResource(R.drawable.blue)
                    "Y" -> cell.setBackgroundResource(R.drawable.yellow)
                    "G" -> cell.setBackgroundResource(R.drawable.green)
                    "O" -> cell.setBackgroundResource(R.drawable.orange)
                    "W" -> cell.setBackgroundResource(R.drawable.white)
                }
                cellId++
            }
        }
        when (picture.top) {
            "R" -> top.setBackgroundResource(R.drawable.red)
            "B" -> top.setBackgroundResource(R.drawable.blue)
            "Y" -> top.setBackgroundResource(R.drawable.yellow)
            "G" -> top.setBackgroundResource(R.drawable.green)
            "O" -> top.setBackgroundResource(R.drawable.orange)
            "W" -> top.setBackgroundResource(R.drawable.white)
        }
        when (picture.front) {
            "R" -> front.setBackgroundResource(R.drawable.red)
            "B" -> front.setBackgroundResource(R.drawable.blue)
            "Y" -> front.setBackgroundResource(R.drawable.yellow)
            "G" -> front.setBackgroundResource(R.drawable.green)
            "O" -> front.setBackgroundResource(R.drawable.orange)
            "W" -> front.setBackgroundResource(R.drawable.white)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val play = findViewById<LottieAnimationView>(R.id.play)
        play.progress = 0f
    }
}