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

class SavedAdapter(private val list: List<Article>
, val savedViewModel:SavedViewModel) :
    RecyclerView.Adapter<SavedAdapter.news>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

    // to give the differ our data(the list)

    fun submitList(list: List<Article>){
        differ.submitList(list)
    }

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
        Picasso.get().load(item.urlToImage).into(holder.saveImage)
         holder.name.text = item.author
        holder.title.text = item.title
        holder.description. text = item.description

        holder.saveImageButton.setOnClickListener{
           savedViewModel.addMyNewsLiveData(item)

        }
        holder.deleteImageButton.setOnClickListener {
            var delete = mutableListOf<Article>()
            delete.addAll(differ.currentList)
            delete.remove(item)
            differ.submitList(delete.toList())
            savedViewModel.deleteMyNews(item)
        }

   }

    override fun getItemCount(): Int {
        return list.size
//        differ.size
    }




    class news(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var saveImage:ImageView = itemView.findViewById(R.id.saved_imageView)
        var name:TextView = itemView .findViewById(R.id.save_name_textview)
        var title : TextView = itemView .findViewById(R.id.save_title_textview)
        var description :TextView = itemView.findViewById(R.id.save_description_textview)
        var saveImageButton :TextView = itemView.findViewById(R.id.save_news_ImageButton)
        var deleteImageButton: ImageButton = itemView.findViewById(R.id.delete_image_Button)
    }
}
