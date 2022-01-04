package com.newsApp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.newsApp.R
import com.newsApp.databinding.FragmentSavedNewsBinding
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.view.adapters.SavedAdapter
import com.newsApp.view.main.SavedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class SavedNewsFragment : Fragment() {
      private lateinit var binding: FragmentSavedNewsBinding

    private  lateinit var savedAdapter : SavedAdapter
      private lateinit var selectedItem:SavedViewModel
     val list = mutableListOf<SaveNews>()
      private val savedViewModel:SavedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       observer()
        savedViewModel.callMyNews()

        savedAdapter = SavedAdapter(list,SavedViewModel())
//        here
        binding.savednewsRecyclerview.adapter = savedAdapter




    }

//  private fun SavedAdapter(list: MutableList<SaveNews>): SavedAdapter{}



    fun observer(){
        savedViewModel.saveLiveData.observe(viewLifecycleOwner,{


            list.addAll(it)




              
            })

          savedViewModel.saveErrorLiveData.observe(viewLifecycleOwner,{
              it?.let {
                  Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
              }
          })


        }
       
    }
    

