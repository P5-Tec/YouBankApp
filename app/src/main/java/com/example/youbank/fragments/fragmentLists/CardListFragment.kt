package com.example.youbank.fragments.fragmentLists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.youbank.Adapters.CardAdapter
import com.example.youbank.Adapters.TransactionAdapter
import com.example.youbank.R
import com.example.youbank.fragments.dummy.DummyContent
import com.example.youbank.fragments.recyclerViewAdapters.MyCardRecyclerViewAdapter
import com.example.youbank.room.viewmodels.TransactionListViewModel
import com.example.youbank.viewModels.CardListViewModel
import kotlin.math.log

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

                //adapter = CardAdapter(DummyContent.ITEMS)
                cardAdapter = CardAdapter()
                adapter = cardAdapter
                model.allCards.observe(viewLifecycleOwner, {it?.let{ cardAdapter.submitList(it) }})

                Log.i("items", adapter?.itemCount.toString())
                with(adapter){
                }
            }
        }
        return view
    }
}