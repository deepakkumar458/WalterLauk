package com.demo.walterlauk.model

data class LoginData(
    val authorization_token: String,
    val created_at: String,
    val device_token: String,
    val device_type: String,
    val driver_phone_number: String,
    val driver_type: String,
    val email: Any,
    val email_verified_at: Any,
    val id: Int,
    val imageName: String,
    val image_url: String,
    val is_active: String,
    val is_admin: String,
    val name: String,
    val updated_at: String,
    val user_name: String
)