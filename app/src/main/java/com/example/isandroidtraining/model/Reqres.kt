package com.example.isandroidtraining.model


import com.example.isandroidtraining.model.Ad
import com.example.isandroidtraining.model.Data
import com.google.gson.annotations.SerializedName

data class Reqres(
    @SerializedName("ad")
    val ad: Ad,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)