package com.ruhlan.lafyuuapplication.service

import com.ruhlan.lafyuuapplication.model.ProductResponse
import com.ruhlan.lafyuuapplication.model.UserInformationRequestBody
import com.ruhlan.lafyuuapplication.model.UserInformationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by r.usubov on 15.02.25.
 */

/**
 * https://dummyjson.com
 * /auth/login
 */
interface LafyuuService {
    @POST("/auth/login")
    fun loginUser(
        @Body userInformationRequestBody: UserInformationRequestBody
    ): Call<UserInformationResponse>


    @GET("/products")
    fun getAllProducts() : Call<ProductResponse>
}

