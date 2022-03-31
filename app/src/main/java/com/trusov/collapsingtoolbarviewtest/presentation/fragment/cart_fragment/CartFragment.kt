package com.trusov.collapsingtoolbarviewtest.presentation.fragment.cart_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.trusov.collapsingtoolbarviewtest.App
import com.trusov.collapsingtoolbarviewtest.databinding.FragmentCartBinding
import com.trusov.collapsingtoolbarviewtest.presentation.util.NavigationController
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter.FoodItemAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }
    private lateinit var navController: NavigationController

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding == null")

    override fun onAttach(context: Context) {
        (context.applicationContext as App).component.inject(this)
        super.onAttach(context)
        if (context is NavigationController) {
            navController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listOfFoodItems.observe(viewLifecycleOwner) {
            binding.rvOrderedFoodItems.adapter = FoodItemAdapter().apply {
                submitList(it)
                onFoodItemLongClickListener = {
                    viewModel.orderItem(it)
                }
                onFoodItemClickListener = {
                    navController.launchFoodItemDetailedFragment(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListOfOrderedFoodItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}