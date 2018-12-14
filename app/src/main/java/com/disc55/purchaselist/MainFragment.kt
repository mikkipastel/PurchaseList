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
import com.disc55.purchaselist.model.Purchase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment: Fragment(), PurchaseListListener {

    private lateinit var mFirestore: FirebaseFirestore
    private var item = arrayListOf<Purchase>()

    private lateinit var mAdapter: PurchaseListAdapter

    companion object {
        fun newInstant() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFirestore = FirebaseFirestore.getInstance()

        setDataAdapter()

        btnPurchaseFragment.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.container, PurchaseFragment.newInstant())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setDataAdapter() {
        mFirestore.collection("Users").document("Disc")
            .collection("Locations").document("BigC")
            .collection("Items").whereLessThanOrEqualTo("status",1)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    item.add(
                        Purchase(
                            document.data[Constant().textCollectionItemName].toString(),
                            document.data[Constant().textCollectionQuantity].toString().toFloat(),
                            document.data[Constant().textCollectionUnit].toString(),
                            document.data[Constant().textCollectionStatus].toString().toInt(),
                            Timestamp(Date()),
                            Timestamp(Date())
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                textDisp.text = getString(R.string.failure_get_firestore, exception)
            }

        val listAdapter = PurchaseListAdapter(item, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = true
            adapter = listAdapter
            onFlingListener = null
        }
    }

    override fun onItemDoneClick(position: Int, isClick: Boolean) {
        //TODO: done item
        val currentViewHolder = recyclerView.findViewHolderForAdapterPosition(position) as PurchaseListAdapter.ViewHolder

        if (isClick) {
            mAdapter.changeStatusOpenToClose(currentViewHolder)
        } else {
            mAdapter.changeStatusCloseToOpen(currentViewHolder)
        }
    }

    override fun onItemRemoveClick(position: Int) {
        //TODO: remove item
        mAdapter.changeStatusOpenToDelete(position)
    }

}