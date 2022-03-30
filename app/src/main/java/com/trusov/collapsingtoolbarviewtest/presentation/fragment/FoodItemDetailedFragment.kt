package com.trusov.collapsingtoolbarviewtest.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.trusov.collapsingtoolbarviewtest.App
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.FoodItemDetailedViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class FoodItemDetailedFragment : Fragment(R.layout.fragment_food_item_detailed) {

    private lateinit var item: FoodItem
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FoodItemDetailedViewModel::class.java]
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<TextView>(R.id.tv_order_button)
        viewModel.getItem(item.id)
        viewModel.item.observe(viewLifecycleOwner) { loadedItem ->
            Picasso.get().load(loadedItem.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view.findViewById<ImageView>(R.id.iv_food_big_image))
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

    companion object {
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