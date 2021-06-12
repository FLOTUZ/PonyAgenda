package com.example.ponyagenda.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContactItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String
): Serializable