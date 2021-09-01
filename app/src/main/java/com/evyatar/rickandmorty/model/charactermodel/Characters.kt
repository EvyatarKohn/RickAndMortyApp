package com.evyatar.rickandmorty.model.charactermodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    @Expose
    val info: Info,

    @SerializedName("results")
    @Expose
    val results: List<Result>
)