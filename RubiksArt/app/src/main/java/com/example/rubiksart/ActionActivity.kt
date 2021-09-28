package com.example.rubiksart

import android.animation.Animator
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.rubiksart.program.Answer

class ActionActivity : AppCompatActivity() {
    var picture: Answer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        picture = intent.getSerializableExtra("picture") as Answer?
        var num = 0
        showStep(picture, num)
    }

    fun showStep(picture: Answer?, num: Int) {
        var prev = findViewById<LottieAnimationView>(R.id.prev)
        var next = findViewById<LottieAnimationView>(R.id.next)
        var actions_left = findViewById<TextView>(R.id.actions_left)
        var cell = findViewById<ImageView>(R.id.cell11)
        var action = findViewById<ImageView>(R.id.action)
        var animation = AnimationUtils.loadAnimation(this, R.anim.image_click)
        actions_left.text = "Rotations left: "+(picture!!.answer.size-num-1).toString()
        var step = picture.answer[num]
        var cellId = 1
        if (num != picture.answer.size-1) {
            var act = picture.action[num + 1]
            when (act) {
                "UR" -> action.setBackgroundResource(R.drawable.ur)
                "UL" -> action.setBackgroundResource(R.drawable.ul)
                "DR" -> action.setBackgroundResource(R.drawable.dr)
                "DL" -> action.setBackgroundResource(R.drawable.dl)
                "RU" -> action.setBackgroundResource(R.drawable.ru)
                "RD" -> action.setBackgroundResource(R.drawable.rd)
                "LU" -> action.setBackgroundResource(R.drawable.lu)
                "LD" -> action.setBackgroundResource(R.drawable.ld)
                "FC" -> action.setBackgroundResource(R.drawable.fc)
                "FAC" -> action.setBackgroundResource(R.drawable.fac)
            }
        }
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
                when (step[i][j]) {
                    "R" -> cell.setBackgroundResource(R.drawable.red)
                    "B" -> cell.setBackgroundResource(R.drawable.blue)
                    "Y" -> cell.setBackgroundResource(R.drawable.yellow)
                    "G" -> cell.setBackgroundResource(R.drawable.green)
                    "O" -> cell.setBackgroundResource(R.drawable.orange)
                    "W" -> cell.setBackgroundResource(R.drawable.white)
                }
                cell.startAnimation(animation)
                /*animation.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                    }
                    override fun onAnimationEnd(animation: Animation?) {
                    }
                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })*/
                cellId++
            }
        }
        if (num == picture.answer.size-1){
            next.setImageResource(R.color.background)
            action.setBackgroundResource(R.color.background)
            actions_left.text = "Solution is found!"
        }
        else {
            next.setAnimation(R.raw.next)
            next.setOnClickListener {
                next.speed = 2F
                next.playAnimation()
                next.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                        var n = num
                        n++
                        showStep(picture, n)
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
            }
        }
        if (num == 0){
            prev.setImageResource(R.color.background)
        }
        else {
            prev.setAnimation(R.raw.prev)
            prev.setOnClickListener {
                prev.speed = 2F
                prev.playAnimation()
                prev.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                        var n = num
                        n--
                        showStep(picture, n)
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
            }
        }
    }
}
/*
class ActionActivity : AppCompatActivity() {
    var picture: Answer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        picture = intent.getSerializableExtra("picture") as Answer?
        var num = 0
        showStep(picture, num)
    }

    fun showStep(picture: Answer?, num: Int) {
        var prev = findViewById<LottieAnimationView>(R.id.prev)
        var next = findViewById<LottieAnimationView>(R.id.next)
        var actions_left = findViewById<TextView>(R.id.actions_left)
        var cell = findViewById<ImageView>(R.id.cell11)
        var action = findViewById<ImageView>(R.id.action)
        prev.setImageResource(R.drawable.prev)
        next.setImageResource(R.drawable.next)
        actions_left.text = "Rotations left: "+(picture!!.answer.size-num-1).toString()

        var step = picture.answer[num]
        var cellId = 1
        if (num != picture.answer.size-1) {
            var act = picture.action[num + 1]
            when (act) {
                "UR" -> action.setBackgroundResource(R.drawable.ur)
                "UL" -> action.setBackgroundResource(R.drawable.ul)
                "DR" -> action.setBackgroundResource(R.drawable.dr)
                "DL" -> action.setBackgroundResource(R.drawable.dl)
                "RU" -> action.setBackgroundResource(R.drawable.ru)
                "RD" -> action.setBackgroundResource(R.drawable.rd)
                "LU" -> action.setBackgroundResource(R.drawable.lu)
                "LD" -> action.setBackgroundResource(R.drawable.ld)
                "FC" -> action.setBackgroundResource(R.drawable.fc)
                "FAC" -> action.setBackgroundResource(R.drawable.fac)
            }
        }
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
                when (step[i][j]) {
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
        if (num == picture.answer.size-1){
            next.setImageResource(R.color.background)
            next.setOnClickListener{
                next.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click))
            }
            action.setBackgroundResource(R.color.background)
            actions_left.text = "Solution is found!"
        }
        else {
            next.setOnClickListener {
                next.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click))
                var n = num
                n++
                showStep(picture, n)
            }
        }
        if (num == 0){
            prev.setImageResource(R.color.background)
            prev.setOnClickListener {
                prev.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click))
            }
        }
        else {
            prev.setOnClickListener {
                prev.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click))
                var n = num
                n--
                showStep(picture, n)
            }
        }
    }
}
*/