package com.example.ponyagenda.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contacts(
    @SerializedName("contacts")
    val contacts: ArrayList<ContactItem>
): Serializable