package com.disc55.purchaselist.model

import com.google.firebase.Timestamp

class Purchase(
    val id: String,
    val itemName: String,
    val quantity: Float,
    val unit: String,
    var status: Int, //0 open , 1 close , 2 predict , 9 delete
    val requireDate: Timestamp,
    val closeDate: Timestamp
)