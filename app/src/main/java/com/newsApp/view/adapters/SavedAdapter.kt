package com.newsApp.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.newsApp.R
import com.newsApp.model.Article
import com.newsApp.view.main.SavedViewModel
import com.squareup.picasso.Picasso

class SavedAdapter(private val list: MutableList<Article>
, val savedViewModel:SavedViewModel) :
    RecyclerView.Adapter<SavedAdapter.news>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAdapter.news {
        return news(
            LayoutInflater.from(parent.context).inflate(
                R.layout.savednews_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: news, position: Int) {
        val item = list[position]
//      item=differ.currentlist[position]
        if(item.urlToImage.isNotEmpty() && item.urlToImage.isNotBlank())
        {
            Picasso.get().load(item.urlToImage).into(holder.saveImage)
        }
         holder.name.text = item.author
        holder.title.text = item.title
        holder.description. text = item.description

//        holder.saveImageButton.setOnClickListener{
//           savedViewModel.addMyNewsLiveData(item)
//
//        }
        holder.deleteImageButton.setOnClickListener {

            list.remove(item)
            notifyDataSetChanged()
            savedViewModel.deleteMyNews(item)
        }

   }

    override fun getItemCount(): Int {
        return list.size

    }




    class news(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var saveImage:ImageView = itemView.findViewById(R.id.saved_imageView)
        var name:TextView = itemView .findViewById(R.id.save_name_textview)
        var title : TextView = itemView .findViewById(R.id.save_title_textview)
        var description :TextView = itemView.findViewById(R.id.save_description_textview)
//        var saveImageButton :TextView = itemView.findViewById(R.id.save_news_ImageButton)
        var deleteImageButton: ImageButton = itemView.findViewById(R.id.delete_image_Button)
    }
}
