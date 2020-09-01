package com.udacoding.wisataapp.network

import com.udacoding.wisataapp.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface WisataService {

    @GET("api?action=findAll")
    fun getDataWisata():Call<ResponseServer>


}