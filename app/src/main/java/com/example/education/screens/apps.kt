package com.example.education.screens

import com.example.education.models.AppDetails
import com.example.education.models.Category

val apps = listOf(
    AppDetails(
        id = "1",
        name = "Telegram",
        developer = "Telegram FZ-LLC",
        category = Category.SOCIAL,
        ageRating = 12,
        size = 85.5f,
        iconUrl = "https://thumb.wikimedia.org/wikipedia/commons/thumb/8/82/Telegram_logo.svg/1280px-Telegram_logo.svg.png?utm_source=sah.wikipedia.org&utm_campaign=index&utm_content=thumbnail",
        screenshotUrlList = null,
        description = "Мессенджер для общения"
    ),
    AppDetails(
        id = "2",
        name = "VK Музыка",
        developer = "VK",
        category = Category.MUSIC,
        ageRating = 12,
        size = 120.0f,
        iconUrl = "https://cdn-icons-png.flaticon.com/512/733/733583.png",
        screenshotUrlList = null,
        description = "Приложение для прослушивания музыки"
    )
)