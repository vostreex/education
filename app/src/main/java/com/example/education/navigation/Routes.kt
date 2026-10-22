package com.example.education.navigation

import kotlinx.serialization.Serializable


@Serializable
data object MainScreenDestination

@Serializable
data class AppDetailsContentDestination(
    val appId: String
)

