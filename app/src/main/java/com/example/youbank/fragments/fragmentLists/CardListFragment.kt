package com.example.youbank.fragments.fragmentLists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.youbank.R
import com.example.youbank.recyclerViewAdapters.MyCardRecyclerViewAdapter
import com.example.youbank.fragments.dummy.DummyContent

class CardListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

                adapter = MyCardRecyclerViewAdapter(DummyContent.ITEMS)
            }
        }
        return view
    }
}