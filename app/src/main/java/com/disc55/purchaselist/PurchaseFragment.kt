package com.disc55.purchaselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_purchase_item.*

import org.jetbrains.anko.toast

class PurchaseFragment : Fragment() {
    //from this vdo "Adding Data to Cloud Firestore using Kotlin."
    //https://www.youtube.com/watch?v=yKSuB5COWL4
    lateinit var db: DocumentReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val v = inflater.inflate(R.layout.add_purchase_item,container,false)

        // Save data into Cloud Firestore
        db = FirebaseFirestore.getInstance().document("Users/Disc") //edit database under this path
        /*val save = findViewById<View>(R.id.add_purchase_item_btn) as Button
        save.setOnClickListener { view: View? -> save() }    */
//#        add_purchase_item_btn.setOnClickListener {
//#            save()
//#        }
        return inflater.inflate(R.layout.add_purchase_item,container,false)
    }

    private fun save() {
        /*
        val itemname_edit_txt = findViewById<View>(R.id.itemname_edit_txt) as EditText
        val quantity_edit_txt = findViewById<View>(R.id.quantity_edit_txt) as EditText
        val unit_edit_txt = findViewById<View>(R.id.quantity_edit_txt) as EditText
        val location_edit_txt = findViewById<View>(R.id.location_edit_txt) as EditText
        */
        val edittextItemName = R.id.edittextItemName as EditText
        val edittextQuantity = R.id.edittextQuantity as EditText
        val edittextUnit = R.id.edittextUnit as EditText
        val edittextRequireDate = R.id.edittextRequireDate as EditText
        val edittextLocation = R.id.edittextLocation as EditText

        val itemName = edittextItemName.text.toString().trim()
        val quantity = edittextQuantity.text.toString().trim()
        val unit = edittextUnit.text.toString().trim()
        val requireDate = edittextRequireDate.text.toString().trim()
        val location = edittextLocation.text.toString().trim()

        if( (!itemName.isEmpty()) && (!quantity.isEmpty()) ){
            try {
                Toast.makeText(context,"OK", Toast.LENGTH_SHORT).show()
                val items = HashMap<String, Any>()
                items.put("itemname",itemName)
                items.put("quantity",quantity)
                items.put("unit",unit)

                //generate auto ID of item for every items in the collection
                //val ref = db.collection("Locations").document(location).collection("Items").document()
                //val ItemId = ref.id
                val ItemId = db.collection("Locations").document(location).collection("Items").document().id
                db.collection("Locations").document(location).collection("Items").document(ItemId).set(items).addOnSuccessListener {
                        void: Void? ->  Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                        exception: java.lang.Exception -> Toast.makeText(context,exception.toString(), Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                Toast.makeText(context,e.toString(), Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(context,"Please fill up the fields", Toast.LENGTH_LONG).show()
        }



    }


}