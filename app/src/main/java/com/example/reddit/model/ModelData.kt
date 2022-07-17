package com.example.reddit.model

data class ModelData(
    val children: List<ModelContainer>, val after: String?, val before: String?
)
