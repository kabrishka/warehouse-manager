package com.kabrishka.warehousemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kabrishka.warehousemanager.R
import com.kabrishka.warehousemanager.model.Shoe

class ShoesAdapter(private val dataset: List<Shoe>): RecyclerView.Adapter<ShoesAdapter.ShoesViewHolder>() {

    class ShoesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTextView)
        val code: TextView = view.findViewById(R.id.codeTextView)
        val brand: TextView = view.findViewById(R.id.brandTextView)
        val size: TextView = view.findViewById(R.id.sizeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shoe, parent, false)

        return ShoesViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        val item = dataset[position]
        holder.name.text =  item.name
        holder.code.text = item.vendorCode
        holder.brand.text = item.brand
        holder.size.text = item.size
    }

    override fun getItemCount() = dataset.size
}