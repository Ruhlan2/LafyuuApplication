package com.ruhlan.lafyuuapplication.service

import com.ruhlan.lafyuuapplication.model.Product
import com.ruhlan.lafyuuapplication.model.ProductResponse
import com.ruhlan.lafyuuapplication.model.UserInformationModel
import com.ruhlan.lafyuuapplication.model.UserInformationRequestBody
import com.ruhlan.lafyuuapplication.model.UserInformationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
    fun getAllProducts(): Call<ProductResponse>

    @GET("users/{id}")
    fun getUserInformation(
        @Path("id") id: Int
    ): Call<UserInformationModel>

    @GET("products/{id}")
    fun getSingleProduct(
        @Path("id") id: Int
    ) : Call<Product>

    @GET("products/search")
    fun searchProduct(
        @Query("q") searchQuery : String
    ) : Call<ProductResponse>

    // products?sortBy=id&order=desc
    @GET("products")
    fun sortProducts(
        @Query("sortBy") sortBy : String,
        @Query("order") order : String// asc/desc
    ) : Call<ProductResponse>
}

