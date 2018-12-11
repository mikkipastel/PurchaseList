package com.disc55.purchaselist

import com.google.firebase.Timestamp

class Purchase(
    val itemName: String,
    val quantity: Float,
    val unit: String,
    val status: String,
    val requireDate: Timestamp
)