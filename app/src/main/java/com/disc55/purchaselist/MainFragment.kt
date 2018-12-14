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

        //TODO: add firestore
        db = FirebaseFirestore.getInstance()

        /*
        public fun getQuotesFromFireStore(){
            db.collection(FirebaseConstants.COLLECTION_PARENT)
                .get()
                .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot>) {
                    override fun onComplete(task: Task<QuerySnapshot>){
                        if(task.isSuccessful){
                            for(document in task.result){
                                if(document.id==FirebaseConstants.DOCUMENT_PARENT){
                                    document.data[FirebaseConstants.]
                                }
                                Log.d(TAG,document.id)
                            }
                        }else{

                        }
                    }
                }
        }
*/
        //val disp: TextView = findViewById(R.id.dispTxt) as TextView
        val disp = R.id.dispTxt as TextView

        //Get Data in a Collection and a Document
        disp.text = "OK"
        //disp.setText("OK")
        /*val docRef = db.collection("Users").document("Disc")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    //Log.d(TAG, "DocumentSnapshot data: " + document.data)
                    disp.text = document.data.toString()


                } else {
                    //Log.d(TAG, "No such document")
                    disp.text = "No such document"
                }
            }
            .addOnFailureListener { exception ->
                //Log.d(TAG, "get failed with ", exception)
                disp.text = "get failed with"
            }
        */

        //Get all documents in a collection
        /*disp.text = "OK"
        db.collection("Users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    disp.text = disp.text.toString() + " /#/ " + document.data
                }
            }
            .addOnFailureListener { exception ->
                disp.text = "Error getting documents: " + exception
            }
        */

        //Get multiple documents from query from a collection
        /*disp.text = "OK"
        db.collection("Users").whereEqualTo("name","Mint")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    disp.text = disp.text.toString() + " /#/ " + document.data
                }
            }
            .addOnFailureListener { exception ->
                disp.text = "Error getting documents: " + exception
            }
        */


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