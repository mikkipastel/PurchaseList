package com.disc55.purchaselist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disc55.purchaselist.Purchase
import com.disc55.purchaselist.R
import kotlinx.android.synthetic.main.item_purchase_list.view.*

class PurchaseListAdapter(private val items: ArrayList<Purchase>,
                          private val listener: PurchaseListListener): RecyclerView.Adapter<PurchaseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_purchase_list, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.checkbox.setOnClickListener { listener.onItemDoneClick(position, holder.itemView.checkbox.isChecked) }
        holder.itemView.imgClose.setOnClickListener { listener.onItemRemoveClick(position) }
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(item: Purchase) {
            itemView.textName.text = "${item.itemName} ${item.quantity} ${item.unit}"

            if (item.status == 1) {
                itemView.checkbox.isChecked = true
                itemView.viewLine.visibility = View.VISIBLE
            }
        }
    }

    fun changeStatusOpenToClose(holder: ViewHolder) {
        holder.itemView.apply {
            checkbox.isChecked = true
            viewLine.visibility = View.VISIBLE
        }
    }

    fun changeStatusCloseToOpen(holder: ViewHolder) {
        holder.itemView.apply {
            checkbox.isChecked = false
            viewLine.visibility = View.GONE
        }
    }

    fun changeStatusOpenToDelete(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

}