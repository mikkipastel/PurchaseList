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


        db = FirebaseFirestore.getInstance().document("user2/location") //edit database under this path

        val save = findViewById<View>(R.id.saveBtn) as Button
        save.setOnClickListener {
            view: View? -> save()
        }

    }

    private fun save() {


        val edit_text_title = findViewById<View>(R.id.edit_text_title) as EditText
        val edit_text_description = findViewById<View>(R.id.edit_text_description) as EditText


        val title = edit_text_title.text.toString().trim()
        val description = edit_text_description.text.toString().trim()


        if( (!title.isEmpty()) && (!description.isEmpty()) ){
            try {
                Toast.makeText(this,"OK",Toast.LENGTH_LONG).show()
                val items = HashMap<String, Any>()
                items.put("title",title)
                items.put("description",description)
                db.collection(title).document("BigC").set(items).addOnSuccessListener {
                    void: Void? ->  Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
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
