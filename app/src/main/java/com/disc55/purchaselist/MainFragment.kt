package com.disc55.purchaselist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    private lateinit var db: FirebaseFirestore

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arrayListOf(
            Purchase("ไข่ไก่", 1.0f, "โหล", "open", Timestamp(Date())),
            Purchase("ไก่", 1.0f, "Kg", "open", Timestamp(Date()))
        )

        //TODO: add firestore
        db = FirebaseFirestore.getInstance()

        item.add(Purchase("หมู", 2.0f, "Kg", "open", Timestamp(Date())))

        db.collection("Users").document("Disc")
            .collection("Locations").document("BigC")
            .collection("Items")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    item.add(Purchase(document.data["itemname"].toString(),2.0f,"Kg","open", Timestamp(Date())))
                }
            }
            .addOnFailureListener { exception ->
            }

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