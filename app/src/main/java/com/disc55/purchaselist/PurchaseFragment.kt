package com.disc55.purchaselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_purchase.*

class PurchaseFragment : Fragment() {
    //from this vdo "Adding Data to Cloud Firestore using Kotlin."
    //https://www.youtube.com/watch?v=yKSuB5COWL4

    private lateinit var database: DocumentReference

    companion object {
        fun newInstant() = PurchaseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_purchase,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseFirestore.getInstance().document("Users/Disc")

        buttonAddPurchaseItem.setOnClickListener {
            saveItemToFireStore()
        }
    }

    private fun saveItemToFireStore() {

        val itemName = edittextItemName.text.toString().trim()
        val quantity = edittextQuantity.text.toString().trim()
        val unit = edittextUnit.text.toString().trim()
        val requireDate = edittextRequireDate.text.toString().trim()
        val location = edittextLocation.text.toString().trim()

        if( (!itemName.isEmpty()) && (!quantity.isEmpty()) ){
            try {
                Toast.makeText(context,"OK", Toast.LENGTH_SHORT).show()
                val items = HashMap<String, Any>()
                items["itemname"] = itemName
                items["quantity"] = quantity
                items["unit"] = unit

                //generate auto ID of item for every items in the collection
                val reference = database.collection("Locations").document(location).collection("Items")
                val itemId = reference.id

                reference.document(itemId).set(items).apply {
                    addOnSuccessListener {
                        Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
                    }
                    addOnFailureListener {
                        Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e:Exception) {
                Toast.makeText(context,e.toString(), Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(context,"Please fill up the fields", Toast.LENGTH_LONG).show()
        }

    }


}