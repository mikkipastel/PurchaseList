package com.disc55.purchaselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Timestamp
import androidx.fragment.app.Fragment

class PurchaseFragment : Fragment(
/*    val itemName: String,
    val quantity: Float,
    val unit: String,
    val status: String,
    val requireDate: Timestamp
*/
)
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_purchase_item,container,false)
    }


}