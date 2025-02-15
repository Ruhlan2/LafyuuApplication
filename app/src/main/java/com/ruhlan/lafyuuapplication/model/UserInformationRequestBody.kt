package com.ruhlan.lafyuuapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by r.usubov on 15.02.25.
 */
data class UserInformationRequestBody (
    @SerializedName("username")
    val email : String,
    val password : String
)
