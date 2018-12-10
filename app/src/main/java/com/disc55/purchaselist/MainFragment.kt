package com.disc55.purchaselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.disc55.purchaselist.adapter.PurchaseListAdapter
import com.disc55.purchaselist.adapter.PurchaseListListener
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(), PurchaseListListener {

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO:
        val item = arrayListOf(
            Purchase("ไข่ไก่", 1.0f, "โหล", "ตลาด"),
            Purchase("ไข่ไก่", 1.0f, "โหล", "ตลาด")
        )

        val listAdapter = PurchaseListAdapter(item, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = listAdapter
            onFlingListener = null
        }
    }

    override fun onItemDoneClick() {
        //TODO: done item
    }

    override fun onItemRemoveClick() {
        //TODO: remove item
    }

}