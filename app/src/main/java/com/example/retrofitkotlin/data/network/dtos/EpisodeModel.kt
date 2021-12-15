package com.example.retrofitkotlin.data.network.dtos

import com.example.retrofitkotlin.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class EpisodeModel(

    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("created") val created: String,
    @SerializedName("air_date") val air_date: String

): IBaseDiffModel
