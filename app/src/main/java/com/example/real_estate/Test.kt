package com.example.real_estate


import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("info")
    val info: Info,
    @SerializedName("products")
    val products: List<Product>
) {
    data class Info(
        @SerializedName("hasNextPage")
        val hasNextPage: Boolean,
        @SerializedName("hasPrevPage")
        val hasPrevPage: Boolean,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("nextPage")
        val nextPage: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("pagingCounter")
        val pagingCounter: Int,
        @SerializedName("prevPage")
        val prevPage: Any?,
        @SerializedName("totalDocs")
        val totalDocs: Int,
        @SerializedName("totalPages")
        val totalPages: Int
    )

    data class Product(
        @SerializedName("Area")
        val area: Int,
        @SerializedName("_id")
        val id: String,
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Photo")
        val photo: String?,
        @SerializedName("Price")
        val price: Int?,
        @SerializedName("Region")
        val region: String?,
        @SerializedName("request")
        val request: Request
    ) {
        data class Request(
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )
    }
}