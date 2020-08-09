package com.nanthu.goldenratio

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_applications.*
import kotlinx.android.synthetic.main.activity_main_page.bottomBar

class ApplicationsActivity : AppCompatActivity() {

    val context = this //Context of this Activity
    private var galleryList = ArrayList<GalleryModel>()
    private val filterTopics = ArrayList<Int>()
    private var imagesGallery = GalleryItem.getImages()
    private val multiItems = arrayOf("All", "Flowers", "Nature", "Architecture", "Space", "Paintings", "Human Body", "Websites", "Logo", "Photography", "Posters")
    private var checkedItems = booleanArrayOf(false, false, false, false, false, false, false, false, false, false, false)
    private var intial = true
    private val tempt = ArrayList<GalleryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applications)

        //Bottom Bar handler
        bottomBar.selectedItemId = R.id.applications
        bottomBar.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.applications -> return true
                    R.id.home -> {
                        startActivity(Intent(context, MainPage::class.java))
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

        //Setting Up Gallery
        for (i in imagesGallery.indices){
            galleryList.add(GalleryModel(imagesGallery[i].galleryImg))
        }

        val customGallery = CustomGallery(galleryList, context)
        galleryGrid.adapter = customGallery

        val mDialog = Dialog(this)
        mDialog.setContentView(R.layout.dialog)
        val mDialogImage = mDialog.findViewById<ImageView>(R.id.dialogImage)

        galleryGrid.setOnItemClickListener { parent, view, position, id ->

            if(intial){
                galleryList[position].img?.let { mDialogImage.setImageResource(it) }
                mDialog.show()
            } else{
                tempt[position].img?.let { mDialogImage.setImageResource(it) }
                mDialog.show()
            }

        }
    }

    //On Resume Handler
    override fun onResume() {
        super.onResume()
        bottomBar.selectedItemId = R.id.applications
    }

    //Custom Gallery
    class CustomGallery(
        private var itemGallery: ArrayList<GalleryModel>,
        var context : Context
    ): BaseAdapter(){

        private var layoutInflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {


            var view = view
            if (view == null){
                view = layoutInflator.inflate(R.layout.row_item, viewGroup, false)
            }

            val tvImageName = view?.findViewById<ImageView>(R.id.imageViewRow)
            tvImageName?.setImageResource(itemGallery[position].img!!)

            return view!!

        }

        override fun getItem(position: Int): Any {
            return itemGallery[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return itemGallery.size
        }

    }

    //Filtering Gallery
    fun filterFun(view: View){

        MaterialAlertDialogBuilder(this)
            .setTitle("Filter via Category")
            .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->

                if(filterTopics.contains(0) || filterTopics.isEmpty()){
                    filterTopics.apply {
                        clear()
                        add(0)
                        checkedItems = booleanArrayOf(true, false, false, false, false, false, false, false, false, false)
                    }
                }
                setGallery(filterTopics)
            }
            .setMultiChoiceItems(multiItems, checkedItems){ dialog, which, checked ->

                intial = false
                if(checked){
                    filterTopics.add(which)
                }
                if(!checked){
                    if(filterTopics.contains(which)){
                        val index = filterTopics.indexOf(which)
                        filterTopics?.removeAt(index)
                    }
                }

            }
            .show()
    }

    private fun setGallery(filter: ArrayList<Int>){
        tempt.clear()

        loop@ for(i in filter){
            when(i){
                0 -> {
                    val customGallery = CustomGallery(tempt, context)
                    galleryGrid.adapter = customGallery
                    for (i in imagesGallery.indices){
                        tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                    }
                    break@loop
                }

                1 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Flowers"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                2 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Nature"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                3 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Architecture"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                4 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Space"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                5 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Paintings"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                6 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Human Body"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                7 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Websites"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                8 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Logo"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                9 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Photography"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }
                10 -> {
                    for(i in imagesGallery.indices){
                        if(imagesGallery[i].category == "Posters"){
                            tempt.add(GalleryModel(imagesGallery[i].galleryImg))
                        }
                    }
                }

            }
        }

        val customGallery = CustomGallery(tempt, context)
        galleryGrid.adapter = customGallery
    }
}