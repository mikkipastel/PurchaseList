package com.disc55.purchaselist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.google.firebase.firestore.DocumentReference
//import com.google.firebase.database.FirebaseDatabase

public class MainActivity : AppCompatActivity() {

    //Reading Data from Cloud FireStore
//    lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Reading Data from Cloud FireStore
//        val ref = FirebaseDatabase


        // Show fragment add purchase item
       val Change = findViewById<Button>(R.id.btnPurchaseFragment)
        Change.setOnClickListener({
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = PurchaseFragment()
            transaction.replace(R.id.container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        })



    }


}
