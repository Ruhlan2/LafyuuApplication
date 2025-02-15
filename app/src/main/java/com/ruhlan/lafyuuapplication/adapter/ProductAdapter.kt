package com.ruhlan.lafyuuapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruhlan.lafyuuapplication.databinding.ItemProductsBinding
import com.ruhlan.lafyuuapplication.model.Product

/**
 * Created by r.usubov on 15.02.25.
 */
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductVIewHolder>() {

    private val productList = mutableListOf<Product>()

    inner class ProductVIewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product,context:Context) {
            Glide.with(context).load(item.thumbnail).into(binding.productImage)
            binding.productTitle.text = item.title
            binding.productPrice.text = item.price.toString()
            binding.productDiscount.text = "${item.discountPercentage} % Off"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVIewHolder {
        return ProductVIewHolder(
            ItemProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductVIewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item,holder.itemView.context)
    }

    fun updateList(list: List<Product>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}