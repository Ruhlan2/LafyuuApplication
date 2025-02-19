package com.ruhlan.lafyuuapplication.model


import com.google.gson.annotations.SerializedName

data class UserInformationModel(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("maidenName")
    val maidenName: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?
)