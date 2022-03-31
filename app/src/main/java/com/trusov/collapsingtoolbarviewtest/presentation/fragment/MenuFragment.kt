package com.trusov.collapsingtoolbarviewtest.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.*
import com.trusov.collapsingtoolbarviewtest.databinding.FragmentMenuBinding
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.activity.NavigationController
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.FoodItemAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.SaleAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.CategoryAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.MenuViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class MenuFragment : Fragment(R.layout.fragment_menu) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }
    private lateinit var navController: NavigationController

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    override fun onAttach(context: Context) {
        (context.applicationContext as App).component.inject(this)
        super.onAttach(context)
        if (context is NavigationController) {
            navController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSales = view.findViewById<RecyclerView>(R.id.rv_sales)
        rvSales.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rvSales.adapter = SaleAdapter()

        viewModel.listOfFoodItems.observe(viewLifecycleOwner) {
            binding.rvFoods.adapter = FoodItemAdapter().apply {
                submitList(it)
                onFoodItemLongClickListener = {
                    viewModel.orderItem(it)
                }
                onFoodItemClickListener = {
                    navController.launchFoodItemDetailedFragment(it)
                }
            }
        }

        with(binding.rvTags) {
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            viewModel.listOfCategories.observe(viewLifecycleOwner) {
                adapter = CategoryAdapter().apply {
                    submitList(it)
                    onCategoryItemClickListener = {
                        it.isActivated = !it.isActivated
                        viewModel.filterByCategory(it)
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}