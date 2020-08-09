package com.nanthu.goldenratio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val context = this //Context of this activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.raw.gifimage).into(gdHead) //Loading Gif

        //Load and Set Animation for overall cardView
        val transitionDown = AnimationUtils.loadAnimation(context, R.anim.transtiondown)
        cardView.startAnimation(transitionDown)

        //Load and Set Animation for Inner Elements
        val scale = AnimationUtils.loadAnimation(context, R.anim.fadinganim)
        headerText.startAnimation(scale)
        btnStartTour.startAnimation(scale)
        gdHead.startAnimation(scale)

    }

    //Button On Click Function
    fun nextPage(view: View){
        startActivity(Intent(context, MainPage::class.java))
        finish()
    }
}