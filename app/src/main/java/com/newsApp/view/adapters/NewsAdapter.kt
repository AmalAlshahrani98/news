package com.newsApp.view.adapters

import android.media.Image
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.newsApp.R
import com.newsApp.model.Article
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class NewsAdapter(private val list: List<Article>) :
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
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.news_imageView)
        var name: TextView = itemView.findViewById(R.id.news_name_textview)
        var title: TextView = itemView.findViewById(R.id.news_title_textview)
        var description: TextView = itemView.findViewById(R.id.news_description_textview)
    }
}
