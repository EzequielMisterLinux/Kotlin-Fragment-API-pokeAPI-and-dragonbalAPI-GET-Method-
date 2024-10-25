package com.example.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragments.R
import com.example.fragments.model.DragonBallCharacter

class DragonBallAdapter(private val characterList: List<DragonBallCharacter>) :
    RecyclerView.Adapter<DragonBallAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val raceTextView: TextView = view.findViewById(R.id.raceTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.nameTextView.text = character.name
        holder.raceTextView.text = character.race

        Glide.with(holder.imageView.context)
            .load(character.image)
            .into(holder.imageView)
    }

    override fun getItemCount() = characterList.size
}