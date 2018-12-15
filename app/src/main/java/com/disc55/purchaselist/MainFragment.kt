package com.disc55.purchaselist

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.disc55.purchaselist.adapter.PurchaseListAdapter
import com.disc55.purchaselist.adapter.PurchaseListListener
import com.disc55.purchaselist.model.Purchase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment: Fragment(), PurchaseListListener {

    private lateinit var mFirestore: FirebaseFirestore
    private var item = arrayListOf<Purchase>()

    private lateinit var mAdapter: PurchaseListAdapter

    private val refreshTime = 20 * 1000

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

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                setDataAdapter()
                handler.postDelayed(this, (refreshTime).toLong())
            }
        }
        handler.postDelayed(runnable, (refreshTime).toLong())


        btnPurchaseFragment.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.container, PurchaseFragment.newInstant())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setDataAdapter() {
        item.clear()
        mFirestore.collection("Users").document("Disc")
            .collection("Locations").document("BigC")
            .collection("Items").whereLessThanOrEqualTo("status",1)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    item.add(
                        Purchase(
                            document.id,
                            document.data[Constant().textCollectionItemName].toString(),
                            document.data[Constant().textCollectionQuantity].toString().toFloat(),
                            document.data[Constant().textCollectionUnit].toString(),
                            document.data[Constant().textCollectionStatus].toString().toInt(),
                            Timestamp(Date()),
                            Timestamp(Date()))
                    )
                }

                mAdapter = PurchaseListAdapter(item, this)

                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    isNestedScrollingEnabled = true
                    adapter = mAdapter
                    onFlingListener = null
                }
            }
            .addOnFailureListener { exception ->
                textDisp.text = getString(R.string.failure_get_firestore, exception)
            }
    }

    override fun onItemDoneClick(position: Int, isClick: Boolean) {
        val currentViewHolder = recyclerView.findViewHolderForAdapterPosition(position) as PurchaseListAdapter.ViewHolder

        val database = FirebaseFirestore.getInstance().document("Users/Disc")
        val newReference = database.collection("Locations").document("BigC").collection("Items").document(item[position].id)

        if (isClick) {
            //mAdapter.changeStatusOpenToClose(currentViewHolder)
            newReference.update("status",1)
        } else {
            //mAdapter.changeStatusCloseToOpen(currentViewHolder)
            newReference.update("status",0)
        }
        setDataAdapter()
    }

    override fun onItemRemoveClick(position: Int) {
        val database = FirebaseFirestore.getInstance().document("Users/Disc")
        val newReference = database.collection("Locations").document("BigC").collection("Items").document(item[position].id)
        newReference.update("status",9)
        //mAdapter.changeStatusOpenToDelete(position)
        setDataAdapter()
    }

}