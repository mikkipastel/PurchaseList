package com.disc55.purchaselist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstant())
            .commit()

        lateinit var db: FirebaseFirestore
        db = FirebaseFirestore.getInstance()

        val disp = findViewById(R.id.dispTxtTest) as TextView

        //#### Get multiple documents from query from a collection ####/

        //disp.text = "OK"
        /*db.collection("Users").whereEqualTo("name","Mint")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    disp.text = disp.text.toString() + " /#/ " + document.data["id"]
                }
            }
            .addOnFailureListener { exception ->
                disp.text = "Error getting documents: " + exception
            }
        */

        db.collection("Users").document("Disc")
            .collection("Locations").document("BigC")
            .collection("Items")//.whereEqualTo("name","Mint")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    disp.text = disp.text.toString() + " , " + document.data["itemname"]
                }
            }
            .addOnFailureListener { exception ->
                disp.text = "Error getting documents: " + exception
            }

    }

}
