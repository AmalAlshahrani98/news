package com.newsApp.view.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.newsApp.R
import com.newsApp.model.Article
import com.newsApp.view.main.SavedViewModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class SavedAdapter(val context: Context, private val list: MutableList<Article>
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
        holder.description.text = item.description
        holder.editTextNote.setText(item.note)

//        holder.saveImageButton.setOnClickListener{
//          savedViewModel.addMyNewsLiveData(item)
//
//       }
        holder.deleteImageButton.setOnClickListener {

            list.remove(item)
            notifyDataSetChanged()
            savedViewModel.deleteMyNews(item)
        }


        holder.editImageButton.setOnClickListener {
            var text = holder.editTextNote.text.toString()
            item.note = text
            savedViewModel.editMyNews(item)
            holder.editTextNote.isFocusable = false

        }
        ///////////////////////////////////////
        holder.shareImageButton.setOnClickListener {
            val link = item.urlToImage
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT , link)
            context.startActivity(Intent.createChooser(intent , "Share Link"))


        }

    }
    private fun getBitmapFromView(view: ImageView):Bitmap?{
        val bitmap= Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val paint= Canvas(bitmap)
        view.draw(paint)
        return bitmap

    }
    private fun getImageUri(inContext: Context, inImage:Bitmap): Uri?{
        val byte= ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG,100,byte)
        val path= MediaStore.Images.Media.insertImage(inContext.contentResolver,inImage,"Title",null)
        return Uri.parse(path)


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
        var editImageButton :ImageButton =itemView.findViewById(R.id.edit_image_Button)
        var editTextNote :EditText = itemView.findViewById(R.id.edit_text_note)
        var shareImageButton: ImageButton = itemView.findViewById(R.id.ShareImageButton)
    }
}
