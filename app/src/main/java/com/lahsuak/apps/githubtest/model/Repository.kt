package com.lahsuak.apps.githubtest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository(
    val id: Long? = null,
    val name: String? = null,
    @SerializedName("language")
    val language: String?,
    val private: Boolean,
    @SerializedName("full_name") val fullName: String? = null,
    val owner: Owner? = null,
    @SerializedName("stargazers_count")
    val starCount: Int
) : Serializable
