package com.newsApp.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.newsApp.R
import com.newsApp.databinding.FragmentSavedNewsBinding
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.view.adapters.SavedAdapter
import com.newsApp.view.identity.LoginActivity
import com.newsApp.view.main.SavedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.NullPointerException

class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding

    private lateinit var savedAdapter: SavedAdapter
    private lateinit var selectedItem: SavedViewModel
    var list = mutableListOf<Article>()
    private val savedViewModel: SavedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        observer()
        savedViewModel.callMyNews()


        savedAdapter = SavedAdapter(requireActivity(), list, savedViewModel)
//        here
        binding.savednewsRecyclerview.adapter = savedAdapter


    }


    fun observer() {
        savedViewModel.myNewsLiveData.observe(viewLifecycleOwner, {

            list.clear()
            list.addAll(it)


            savedAdapter.notifyDataSetChanged()


        })

        savedViewModel.saveErrorLiveData.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
//menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val logout = menu.findItem(R.id.logout_button)

        try { logout.setOnMenuItemClickListener {

            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()

            true
        } } catch(e:NullPointerException){
            //nothing
        }


    }
}


//Log.d("tag","my log")

