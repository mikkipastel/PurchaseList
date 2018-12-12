package com.disc55.purchaselist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

public class MainActivity : AppCompatActivity() {

    //from this vdo "Adding Data to Cloud Firestore using Kotlin."
    //https://www.youtube.com/watch?v=yKSuB5COWL4
    lateinit var db: DocumentReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //db = FirebaseFirestore.getInstance().document("user2/location") //edit database under this path
        db = FirebaseFirestore.getInstance().document("Users/Disc") //edit database under this path

        val save = findViewById<View>(R.id.add_purchase_item_btn) as Button
        save.setOnClickListener {
                view: View? -> save()
        }

    }

    private fun save() {

        val itemname_edit_txt = findViewById<View>(R.id.itemname_edit_txt) as EditText
        val quantity_edit_txt = findViewById<View>(R.id.quantity_edit_txt) as EditText
        val unit_edit_txt = findViewById<View>(R.id.quantity_edit_txt) as EditText
        val location_edit_txt = findViewById<View>(R.id.location_edit_txt) as EditText

        val itemname = itemname_edit_txt.text.toString().trim()
        val quantity = quantity_edit_txt.text.toString().trim()
        val unit = quantity_edit_txt.text.toString().trim()
        val location = location_edit_txt.text.toString().trim()

        if( (!itemname.isEmpty()) && (!quantity.isEmpty()) ){
            try {
                Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show()

                val items = HashMap<String, Any>()
                items.put("itemname",itemname)
                items.put("quantity",quantity)
                items.put("unit",unit)

                //generate auto ID of item for every items in the collection
                //val ref = db.collection("Locations").document(location).collection("Items").document()
                //val ItemId = ref.id
                val ItemId = db.collection("Locations").document(location).collection("Items").document().id
                db.collection("Locations").document(location).collection("Items").document(ItemId).set(items).addOnSuccessListener {
                        void: Void? ->  Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                        exception: java.lang.Exception -> Toast.makeText(this,exception.toString(),Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(this,"Please fill up the fields",Toast.LENGTH_LONG).show()
        }



    }

}
