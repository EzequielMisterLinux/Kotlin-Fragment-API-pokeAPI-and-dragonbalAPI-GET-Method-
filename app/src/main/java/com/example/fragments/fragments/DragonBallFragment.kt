package com.example.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader
import com.example.fragments.R
import com.example.fragments.adapter.DragonBallAdapter
import com.example.fragments.api.DragonBallApiService
import com.example.fragments.model.DragonBallCharacter
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DragonBallFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DragonBallAdapter
    private val characterList = mutableListOf<DragonBallCharacter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        loadData()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DragonBallAdapter(characterList)
        recyclerView.adapter = adapter
    }

    private fun loadData() {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DragonBallApiService::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Obtenemos el objeto con la lista de personajes
                val response = service.getCharacters()
                val characters = response.items // Accedemos a la lista de personajes

                if (characters.isEmpty()) {
                    Toast.makeText(context, "No characters found", Toast.LENGTH_SHORT).show()
                } else {
                    characterList.clear()
                    characterList.addAll(characters)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: HttpException) {
                Toast.makeText(context, "HTTP Error: ${e.code()}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }


}


