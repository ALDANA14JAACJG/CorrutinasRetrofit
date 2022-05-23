package com.aldana.jorge.corrutinasretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldana.jorge.corrutinasretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: JorgeAdapter
    private val jorgeImages= mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svJorge.setOnQueryTextListener(this)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = JorgeAdapter(jorgeImages)
        binding.rvJorge.layoutManager=LinearLayoutManager(this)
        binding.rvJorge.adapter=adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val images = puppies?.images ?: emptyList()
                    jorgeImages.clear()
                    jorgeImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                 }
                hideKeyboard()
            }


        }

    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this,"Estamos teniendo  un error",Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchByName(query.toLowerCase(Locale.getDefault()))
        }
            return true
    }

    override fun onQueryTextChange(newText: String?): Boolean{
        return true
    }

    }