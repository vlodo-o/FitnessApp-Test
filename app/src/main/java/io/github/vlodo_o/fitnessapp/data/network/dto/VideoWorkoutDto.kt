package io.github.vlodo_o.fitnessapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class VideoWorkoutDto(
    @SerializedName("id") val id: Int,
    @SerializedName("duration") val duration: String,
    @SerializedName("link") val link: String
)