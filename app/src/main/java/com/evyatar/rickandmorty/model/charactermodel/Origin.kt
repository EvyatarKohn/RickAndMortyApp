package com.evyatar.rickandmorty.model.charactermodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("url")
    @Expose
    val url: String
)