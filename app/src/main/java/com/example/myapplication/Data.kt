package com.example.myapplication

import androidx.compose.ui.graphics.Color

// Модель данных
data class AppInfo(
    val name: String,
    val description: String,
    val category: String,
    val iconColor: Color
)

// Список приложений (те самые данные)
val mockApps = listOf(
    AppInfo(
        name = "Telegram",
        description = "Быстрый и безопасный мессенджер.",
        category = "Общение",
        iconColor = Color(0xFF229ED9)
    ),
    AppInfo(
        name = "YouTube",
        description = "Смотрите любимые видео со всего мира.",
        category = "Развлечения",
        iconColor = Color(0xFFFF0000)
    ),
    AppInfo(
        name = "Spotify",
        description = "Слушайте миллионы треков и подкастов.",
        category = "Музыка",
        iconColor = Color(0xFF1DB954)
    ),
    AppInfo(
        name = "Google Maps",
        description = "Навигация и карты по всему миру.",
        category = "Навигация",
        iconColor = Color(0xFF4285F4)
    ),
    AppInfo(
        name = "Notion",
        description = "Заметки, задачи и база знаний в одном месте.",
        category = "Продуктивность",
        iconColor = Color(0xFF000000)
    ),
    AppInfo(
        name = "Figma",
        description = "Дизайн и прототипирование интерфейсов.",
        category = "Работа",
        iconColor = Color(0xFFF24E1E)
    ),
    AppInfo(
        name = "Duolingo",
        description = "Изучайте иностранные языки в игровой форме.",
        category = "Образование",
        iconColor = Color(0xFF58CC02)
    )
)