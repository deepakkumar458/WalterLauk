package com.demo.walterlauk.model

data class TruckParts(
    val category_id: Int,
    val category_name: String,
    val parts: List<Part>
)