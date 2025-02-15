package com.ruhlan.lafyuuapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by r.usubov on 15.02.25.
 */
data class UserInformationResponse(
    @SerializedName("username")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("image")
    val image: String?
)