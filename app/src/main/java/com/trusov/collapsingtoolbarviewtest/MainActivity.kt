package com.trusov.collapsingtoolbarviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trusov.collapsingtoolbarviewtest.fragment.CartFragment
import com.trusov.collapsingtoolbarviewtest.fragment.MenuFragment
import com.trusov.collapsingtoolbarviewtest.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val menuFragment = MenuFragment()
        val profileFragment = ProfileFragment()
        val cartFragment = CartFragment()

        setCurrentFragment(menuFragment)

        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mi_menu -> setCurrentFragment(menuFragment)
                R.id.mi_profile -> setCurrentFragment(profileFragment)
                R.id.mi_cart -> setCurrentFragment(cartFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment_container, fragment)
            commit()
        }
    }

}