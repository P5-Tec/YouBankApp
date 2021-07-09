package com.example.youbank.fragments.listFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.youbank.R
import com.example.youbank.adapters.CardAdapter
import com.example.youbank.viewModels.listViewModels.CardListViewModel

class CardListFragment: Fragment() {

    private var columnCount = 1
    private lateinit var cardAdapter: CardAdapter
    private val model: CardListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.card_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    else -> GridLayoutManager(context, columnCount)
                }

                // Makes it snap to each item preventing continuous scrolling
                val rl: RecyclerView = view.findViewById(R.id.list)
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rl)

                cardAdapter = CardAdapter()
                adapter = cardAdapter

                cardAdapter.onItemClick = {
                    cardAdapter.currentList
                    findNavController().navigate(R.id.action_homeScreenMotionFragment_to_cardOverviewFragment)
                }

                model.allCards.observe(viewLifecycleOwner, { it?.let { cardAdapter.submitList(it) } })
            }
        }
        return view
    }
}