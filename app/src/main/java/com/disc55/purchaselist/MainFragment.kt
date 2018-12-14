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
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment: Fragment(), PurchaseListListener {

    private lateinit var mFirestore: FirebaseFirestore

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: add firestore
        mFirestore = FirebaseFirestore.getInstance()

        val item = arrayListOf(
            Purchase("ไข่ไก่", 1.0f, "โหล", "open", Timestamp(Date())),
            Purchase("ไข่ไก่", 1.0f, "โหล", "open", Timestamp(Date()))
        )

        val listAdapter = PurchaseListAdapter(item, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = listAdapter
            onFlingListener = null
        }

        btnPurchaseFragment.setOnClickListener {
            savedInstanceState ?: fragmentManager!!.beginTransaction()
                .replace(R.id.container, PurchaseFragment.newInstant())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onItemDoneClick() {
        //TODO: done item
    }

    override fun onItemRemoveClick() {
        //TODO: remove item
    }

}