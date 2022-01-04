package com.newsApp.view.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.newsApp.R
import com.newsApp.model.Article
import com.newsApp.view.NewsViewModel
import com.squareup.picasso.Picasso

class NewsAdapter(private val list: List<Article>,val viewModel: NewsViewModel) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list[position]


      Picasso.get().load(item.urlToImage).into(holder.image)
        holder.name.text = item.author
        holder.title.text = item.title
        holder.description.text = item.description

       holder.saveImageButton.setOnClickListener{
          viewModel.addMyNewsLiveData(viewModel)
           Log.d("tag","my log")


        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.news_imageView)
        var name: TextView = itemView.findViewById(R.id.news_name_textview)
        var title: TextView = itemView.findViewById(R.id.news_title_textview)
        var description: TextView = itemView.findViewById(R.id.news_description_textview)
        var saveImageButton: ImageButton = itemView.findViewById(R.id.save_news_ImageButton)
    }
}
