package com.trusov.collapsingtoolbarviewtest.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.*
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.FoodRvAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.RvAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.CategoryAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.MenuViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class MenuFragment : Fragment(R.layout.fragment_menu) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSales = view.findViewById<RecyclerView>(R.id.rv_sales)
        rvSales.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val list = listOf<FoodItem>(
            FoodItem(1, "", "", "", 0),
            FoodItem(2,"", "", "", 0),
            FoodItem(3,"", "", "", 0),
            FoodItem(4,"", "", "", 0),
            FoodItem(5,"", "", "", 0),
            FoodItem(6,"", "", "", 0),
            FoodItem(7,"", "", "", 0),
            FoodItem(8,"", "", "", 0),
            FoodItem(9,"", "", "", 0),
            FoodItem(10,"", "", "", 0)
        )
        rvSales.adapter = RvAdapter().apply {
            submitList(list)
        }

        viewModel.getListOfFoodItems()
        val rvFoods = view.findViewById<RecyclerView>(R.id.rv_foods)
        viewModel.listOfFoodItems.observe(viewLifecycleOwner) {
            rvFoods.adapter = FoodRvAdapter().apply {
                submitList(it)
            }
        }

        viewModel.getListOfCategories()
        val rvCategories = view.findViewById<RecyclerView>(R.id.rv_tags)
        rvCategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.listOfCategories.observe(viewLifecycleOwner) {
            rvCategories.adapter = CategoryAdapter().apply {
                submitList(it)
                onCategoryItemClickListener = {
                    it.isActivated = !it.isActivated
                    viewModel.filterByCategory(it)
                }
            }
        }


    }
}