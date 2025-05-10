package com.example.innovatevr.models

data class ImgurResponse(
    var data: ImgurData,
    val success: Boolean,
    val status: Int
)
data class ImgurData(
    val link: String
)
