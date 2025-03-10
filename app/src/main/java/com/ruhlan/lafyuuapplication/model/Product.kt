package com.ruhlan.lafyuuapplication.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    @SerializedName("products")
    val products: List<Product>?,
    @SerializedName("total")
    val total: Int?
)

@Parcelize
data class Product(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discountPercentage")
    val discountPercentage: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("returnPolicy")
    val returnPolicy: String?,
    @SerializedName("shippingInformation")
    val shippingInformation: String?,
    @SerializedName("sku")
    val sku: String?,
    @SerializedName("stock")
    val stock: Int?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("warrantyInformation")
    val warrantyInformation: String?,
    @SerializedName("weight")
    val weight: Int?
) : Parcelable