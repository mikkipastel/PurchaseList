package com.disc55.purchaselist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    //Reading Data from Cloud FireStore
//    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Reading Data from Cloud FireStore
//        val ref = FirebaseDatabase

        btnPurchaseFragment.setOnClickListener {
            savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, PurchaseFragment.newInstant())
                .addToBackStack(null)
                .commit()
        }

    }


}
