package com.trusov.collapsingtoolbarviewtest.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.databinding.ActivityMainBinding
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.CartFragment
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.FoodItemDetailedFragment
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.MenuFragment
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.ProfileFragment

class MainActivity : AppCompatActivity(), NavigationController {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSpinner()
        initFragments()
    }

    private fun initFragments() {
        val menuFragment = MenuFragment()
        val profileFragment = ProfileFragment()
        val cartFragment = CartFragment()
        setCurrentFragment(menuFragment)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
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

    private fun initSpinner() {
        binding.spinnerCities.adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            listOf("Москва", "Санкт-Петербург", "Нижний Новгород", "Казань", "Екатерингбург")
        )
    }

    override fun launchFoodItemDetailedFragment(item: FoodItem) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(FoodItemDetailedFragment.NAME)
            replace(R.id.fl_fragment_container, FoodItemDetailedFragment.newInstance(item))
            commit()
        }
    }

}