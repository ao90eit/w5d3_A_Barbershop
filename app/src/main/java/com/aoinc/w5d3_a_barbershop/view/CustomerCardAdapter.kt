package com.aoinc.w5d3_a_barbershop.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aoinc.w5d3_a_barbershop.R
import com.aoinc.w5d3_a_barbershop.data.Customer
import com.aoinc.w5d3_a_barbershop.view.CustomerCardAdapter.CustomerViewHolder

class CustomerCardAdapter(
    private var customerList: MutableList<Customer> = mutableListOf()
) : RecyclerView.Adapter<CustomerViewHolder>() {

    fun updateList(newList: MutableList<Customer>) {
        customerList = newList
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, progress: Float) {
        customerList[position].cutProgress = progress
        notifyItemChanged(position)
    }

    fun addItem(insertedCustomer: Customer){
        customerList.add(insertedCustomer)
        notifyItemInserted(customerList.size - 1)
    }

    fun removeItem(position: Int){
        customerList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.customer_name_textView)
        val progress: ProgressBar = itemView.findViewById(R.id.customer_progressBar)
        val photo: ImageView = itemView.findViewById(R.id.customer_photo_imageView)
        val barber: TextView = itemView.findViewById(R.id.customer_barber_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder =
        CustomerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false))

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customerList[position]

        holder.apply {
            name.text = customer.name
            progress.progress = customer.cutProgress.toInt()
            photo.setImageResource(customer.photoResourceID)

            if (!barber.text.isNullOrBlank())
                barber.text = "cut by ${customer.barberName}"
        }
    }

    override fun getItemCount(): Int = customerList.size
}