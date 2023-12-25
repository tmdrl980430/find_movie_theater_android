package com.example.find_movie_theater.data.entities

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("captionText") val captionText: String
)
