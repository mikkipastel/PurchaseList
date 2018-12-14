package com.disc55.purchaselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_purchase.*
import java.util.*

class PurchaseFragment : Fragment() {
    //from this vdo "Adding Data to Cloud Firestore using Kotlin."
    //https://www.youtube.com/watch?v=yKSuB5COWL4

    private lateinit var reference: DocumentReference

    companion object {
        fun newInstant() = PurchaseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_purchase, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reference = FirebaseFirestore.getInstance().document("Users/Disc")

        buttonAddPurchaseItem.setOnClickListener {
            saveItemToFireStore()
        }
    }

    private fun saveItemToFireStore() {

        val itemName = edittextItemName.text.toString().trim()
        val quantity = edittextQuantity.text.toString().trim()
        val unit = edittextUnit.text.toString().trim()
        val requireDate = Timestamp(Date())
        val location = "BigC"

        if ((!itemName.isEmpty()) && (!quantity.isEmpty()) && (!unit.isEmpty())  && (!location.isEmpty())) {
            try {
                val items = HashMap<String, Any>()
                items[Constant().textCollectionItemName] = itemName
                items[Constant().textCollectionQuantity] = quantity
                items[Constant().textCollectionUnit] = unit
                items[Constant().textCollectionRequireDate] = requireDate
                items[Constant().textCollectionStatus] = 0
                items[Constant().textCollectionCloseDate] = ""

                //generate auto ID of item for every items in the collection
                val reference = reference.collection("Locations").document(location).collection("Items").document()

                val itemId = reference.id

                val newReference = this.reference.collection("Locations").document(location).collection("Items").document(itemId)

                newReference.set(items).apply {
                    addOnSuccessListener {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        activity?.onBackPressed()
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