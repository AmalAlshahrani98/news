package com.newsApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.newsApp.R
import com.newsApp.databinding.FragmentNewsBinding
import com.newsApp.model.Article
import com.newsApp.view.adapters.NewsAdapter


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
lateinit var newsAdapter: NewsAdapter
val list = mutableListOf<Article>()
private val newsViewModel: NewsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        newsViewModel.callNews()
        newsAdapter = NewsAdapter(list)
       binding.newsRecyclerview.adapter = newsAdapter


    }

    fun observer(){
        newsViewModel.newsLiveData.observe(viewLifecycleOwner,{
            list.addAll(it)
            newsAdapter.notifyDataSetChanged()
        })

        newsViewModel.newsErrorLiveData.observe(viewLifecycleOwner,{
            it?.let{
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

}