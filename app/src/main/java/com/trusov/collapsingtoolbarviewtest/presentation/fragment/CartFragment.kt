package com.trusov.collapsingtoolbarviewtest.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.App
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.FoodItemAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.CartViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvFoodItems = view.findViewById<RecyclerView>(R.id.rv_ordered_food_items)
        viewModel.listOfFoodItems.observe(viewLifecycleOwner) {
            rvFoodItems.adapter = FoodItemAdapter().apply {
                submitList(it)
                onFoodItemLongClickListener = {
                    viewModel.orderItem(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListOfOrderedFoodItems()
    }
}