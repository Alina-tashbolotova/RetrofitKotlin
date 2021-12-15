package com.example.retrofitkotlin.data.network.dtos

import com.example.retrofitkotlin.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class CharacterModel(

    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String

): IBaseDiffModel
