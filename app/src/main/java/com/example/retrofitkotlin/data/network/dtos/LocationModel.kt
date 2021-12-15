package com.example.retrofitkotlin.data.network.dtos

import com.example.retrofitkotlin.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationModel(

    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("created") val created: String,
    @SerializedName("type") val type: String

): IBaseDiffModel
