package com.newsApp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.wifi.rtt.CivicLocationKeys.STATE
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.newsApp.MainActivity
import com.newsApp.R
import com.newsApp.databinding.FragmentSavedNewsBinding
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.view.adapters.SavedAdapter
import com.newsApp.view.identity.LoginActivity
import com.newsApp.view.identity.SHARED_PREF_FILE
import com.newsApp.view.identity.USERID
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
   private lateinit var logoutItem: MenuItem

//   private var userID: String = ""
lateinit var sharedPref: SharedPreferences
    lateinit var sharedPrefEditor: SharedPreferences.Editor


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
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPrefEditor = sharedPref.edit()
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
//    MENU

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_button -> {

                //when user logged out this dialog will show
                MaterialAlertDialogBuilder(
                    requireActivity(),
                    android.R.style.ThemeOverlay_Material_Dialog_Alert
                )
                    .setMessage("Are you sure want to Logout ?")
                    //when the user press No the dialog will dismiss
                    .setNegativeButton("No") { dialog, which ->
                        dialog.dismiss()
                    }
                    // when the user press yes the shared pref state "false" -> logout
                    .setPositiveButton("yes") { dialog, which ->
                        sharedPrefEditor.putString(USERID, "")
                        sharedPrefEditor.putBoolean(STATE.toString(), false)
                        sharedPrefEditor.commit()
                        FirebaseAuth.getInstance().signOut()

                        startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
                      //  logoutItem.isVisible = false
                    }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            requireActivity().menuInflater.inflate(R.menu.loguot,menu)



    }
}


//Log.d("tag","my log")

