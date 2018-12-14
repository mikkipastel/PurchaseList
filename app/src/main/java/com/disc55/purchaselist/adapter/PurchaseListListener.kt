package com.disc55.purchaselist.adapter

interface PurchaseListListener {
    fun onItemDoneClick(position: Int, isClick: Boolean)
    fun onItemRemoveClick(position: Int)
}