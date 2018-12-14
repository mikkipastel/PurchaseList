package com.disc55.purchaselist

import com.google.firebase.Timestamp

class Purchase(
    val itemName: String,
    val quantity: Float,
    val unit: String,
    val status: Int, //0 open , 1 close , 2 predict , 9 delete
    val requireDate: Timestamp,
    val closeDate: Timestamp
)