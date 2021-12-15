package com.example.retrofitkotlin.data.network.dtos

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class RickAndMortyResponse<T>(

    @SerializedName("info") val info: Info,
    @SerializedName("results") val results: MutableList<T>

)
