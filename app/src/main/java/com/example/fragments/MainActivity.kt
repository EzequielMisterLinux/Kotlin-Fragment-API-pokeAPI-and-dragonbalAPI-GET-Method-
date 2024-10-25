package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.fragments.PokemonFragment
import com.example.fragments.fragments.DragonBallFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_pokemon -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PokemonFragment())
                        .commit()
                    true
                }
                R.id.nav_dragonball -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DragonBallFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PokemonFragment())
                .commit()
        }
    }
}