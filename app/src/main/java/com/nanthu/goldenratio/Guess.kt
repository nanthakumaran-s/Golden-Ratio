package com.nanthu.goldenratio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_guess.*
import kotlinx.android.synthetic.main.activity_main_page.bottomBar

class Guess : AppCompatActivity() {

    val context = this
    private val answers = ArrayList<String>()
    private var mSelectedOptionPos: Int = 0
    private val crctAnswer = 2
    private lateinit var mselectedTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)


        //Bottom Bar handler
        bottomBar.selectedItemId = R.id.interaction
        bottomBar.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.interaction -> return true
                    R.id.home -> {
                        startActivity(Intent(context, MainPage::class.java))
                        overridePendingTransition(0 , 0)
                        finish()
                        return true
                    }
                    R.id.applications -> {
                        startActivity(Intent(context, ApplicationsActivity::class.java))
                        overridePendingTransition(0 , 0)
                        finish()
                        return true
                    }
                }
                return false
            }
        })


        answers.apply {
            add("521 367 489")
            add("361 752 948")
            add("473 196 258")
            add("854 732 169")
        }

        option1.text = answers[0]
        option2.text = answers[1]
        option3.text = answers[2]
        option4.text = answers[3]

    }

    //On Resume Handler
    override fun onResume() {
        super.onResume()
        bottomBar.selectedItemId = R.id.interaction
    }

    fun selected(view: View){
        when(view.id) {
            R.id.option1 -> selectedTextView(option1, 1)
            R.id.option2 -> selectedTextView(option2, 2)
            R.id.option3 -> selectedTextView(option3, 3)
            R.id.option4 -> selectedTextView(option4, 4)
            R.id.btnCheck -> {

                if (mSelectedOptionPos-1 == crctAnswer){
                    mselectedTv.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                    Toast.makeText(this, "Hurray!! You got it", Toast.LENGTH_SHORT).show()
                }else{
                    mselectedTv.background = ContextCompat.getDrawable(this, R.drawable.wrong_option)
                    option3.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                    Toast.makeText(this, "Oh! You are wrong... Don't worry understand about Golden Ratio More", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun selectedTextView(tv: TextView, selectedNumber: Int){
        defaultOptionsView()
        mselectedTv = tv
        mSelectedOptionPos = selectedNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.default_for_selection)
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, option1)
        options.add(1,option2)
        options.add(2, option3)
        options.add(3, option4)

        for(option in options){
            option.setTextColor(Color.parseColor("#6C6C6C"))
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_for_options)
        }
    }
}