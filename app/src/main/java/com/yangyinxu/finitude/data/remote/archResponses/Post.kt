package com.yangyinxu.finitude.data.remote.archResponses

data class Post(
    val __v: Int,
    val _id: String,
    val content: String,
    val createdAt: String,
    val creator: Creator,
    val imageUrl: String,
    val title: String,
    val updatedAt: String
)