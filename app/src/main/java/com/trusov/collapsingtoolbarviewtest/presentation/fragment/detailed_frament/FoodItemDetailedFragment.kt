package com.trusov.collapsingtoolbarviewtest.presentation.fragment.detailed_frament

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.trusov.collapsingtoolbarviewtest.App
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.databinding.FragmentFoodItemDetailedBinding
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import java.lang.Exception
import javax.inject.Inject

class FoodItemDetailedFragment : Fragment() {

    private lateinit var item: FoodItem

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FoodItemDetailedViewModel::class.java]
    }

    private var _binding: FragmentFoodItemDetailedBinding? = null
    private val binding: FragmentFoodItemDetailedBinding
        get() = _binding ?: throw RuntimeException("FragmentFoodItemDetailedBinding == null")

    override fun onAttach(context: Context) {
        (context.applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable<FoodItem>(ITEM_RC) ?: throw RuntimeException("item == null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodItemDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<TextView>(R.id.tv_order_button)
        viewModel.getItem(item.id)
        viewModel.item.observe(viewLifecycleOwner) { loadedItem ->
            Picasso.get().load(loadedItem.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivFoodBigImage, object : Callback {
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception?) {
                        Picasso.get().load(loadedItem.imageUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .into(binding.ivFoodBigImage)
                        Log.d("Picasso", "Picasso can`t fetch big image")
                    }

                })
            view.findViewById<TextView>(R.id.tv_food_item_title).text = item.title.uppercase()
            view.findViewById<TextView>(R.id.tv_food_item_description).text = item.description
            if (item.isOrdered) {
                button.text = "Удалить из корзины"
            } else {
                button.text = "Добавить в корзину"
            }
            button.setOnClickListener {
                viewModel.orderItem(item)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FoodItemDetailedFragment"
        private const val ITEM_RC = "item_rc"

        fun newInstance(item: FoodItem): FoodItemDetailedFragment {
            return FoodItemDetailedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM_RC, item)
                }
            }
        }
    }


}