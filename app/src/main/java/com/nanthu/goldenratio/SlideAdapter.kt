package com.nanthu.goldenratio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import kotlinx.android.synthetic.main.slide.view.*

class SlideAdapter(private val movies : ArrayList<CardItem>) : CardSliderAdapter<SlideAdapter.MovieViewHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide, parent, false)
        return MovieViewHolder(view)
    }

    override fun bindVH(holder: MovieViewHolder, position: Int) {
        val card = movies[position]
        holder.itemView.run {
            sliderImage.setImageResource(card.image)
            titleSlider.text = card.titleText
            descriptionSlider.text = card.overview
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
}