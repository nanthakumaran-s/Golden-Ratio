package com.nanthu.goldenratio

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.islamkhsh.CardSliderIndicator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {

    val context = this //Context of this Activity

    //ArrayList for Image Carousel
    private val imageSlider = intArrayOf(
        R.drawable.grslider1,
        R.drawable.grslider2,
        R.drawable.grslider3,
        R.drawable.grslider4,
        R.drawable.grslider5,
        R.drawable.grslider6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        setSlider()

        //Initializing Image Carousel
        carouselView.pageCount = imageSlider.size
        carouselView.setImageListener(imageListener)

        //Bottom Bar handler
        bottomBar.selectedItemId = R.id.home
        bottomBar.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.home -> return true
                    R.id.applications -> {
                        startActivity(Intent(context, ApplicationsActivity::class.java))
                        overridePendingTransition(0 , 0)
                        finish()
                        return true
                    }
                    R.id.interaction -> {
                        startActivity(Intent(context, Guess::class.java))
                        overridePendingTransition(0 , 0)
                        finish()
                        return true
                    }
                }
                return false
            }
        })

        info.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.dialog_title))
                .setMessage(resources.getString(R.string.dialog_text))
                .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                }
                .show()
        }

    }

    //Setting Slider
    private fun setSlider(){
        val cardItem = ArrayList<CardItem>()
        cardItem.add(
            CardItem(
                R.drawable.griluminati2,
                resources.getString(R.string.slider1_title),
                resources.getString(R.string.slider1_description)
            )
        )
        cardItem.add(
            CardItem(
                R.drawable.slider2,
                resources.getString(R.string.slider2_title),
                resources.getString(R.string.slider2_description)
            )
        )
        cardItem.add(
            CardItem(
                R.drawable.slider4,
                resources.getString(R.string.slider3_title),
                resources.getString(R.string.slider3_description)
            )
        )
        cardItem.add(
            CardItem(
                R.drawable.slider3,
                resources.getString(R.string.slider4_title),
                resources.getString(R.string.slider4_description)
            )
        )
        cardItem.add(
            CardItem(
                R.drawable.slider5,
                resources.getString(R.string.slider5_title),
                resources.getString(R.string.slider5_description)
            )
        )
        viewPager.adapter = SlideAdapter(cardItem)
        indicator.indicatorsToShow = CardSliderIndicator.UNLIMITED_INDICATORS
    }

    //Setting Up the Image Change Listener
    private var imageListener: ImageListener = ImageListener { position, imageView ->
        imageView.setImageResource(imageSlider[position])
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }

    //Direction to video activity
    fun videoPage(view: View){
        startActivity(Intent(this, VideoPage::class.java))
    }

    //On Resume Handler
    override fun onResume() {
        super.onResume()
        bottomBar.selectedItemId = R.id.home
    }
}