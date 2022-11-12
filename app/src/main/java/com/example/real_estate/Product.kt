package com.example.real_estate

data class Product(
    val Area: Int,
    val Name: String,
    val Sale: String,
    val minPrice: Int,
    val maxPrice: Int,
    val minArea: Int,
    val maxArea: Int,
    val Photo: String,
    val Price: Int,
    val Region: String,
    val _id: String,
    val request: Request
)