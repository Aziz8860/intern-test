package com.ariqbelajar.interntest.network

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Users(
    val perPage: Int? = null,
    val total: Int? = null,
    val data: List<DataItem?>? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val support: Support? = null
)

@Parcelize
@Entity(tableName = "user")
data class DataItem(

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("avatar")
    val avatar: String? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("email")
    val email: String? = null
) : Parcelable

data class Support(

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
