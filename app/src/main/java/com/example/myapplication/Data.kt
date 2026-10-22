package com.example.myapplication

import androidx.compose.ui.graphics.Color

data class AppInfo(
    val id: Int, // Добавили ID для навигации
    val name: String,
    val description: String,
    val category: String,
    val iconColor: Color,
    val isShowcase: Boolean = false // Флаг: приложение из витрины?
)

// Основной список (твои приложения)
val mockApps = listOf(
    AppInfo(1, "Telegram", "Быстрый и безопасный мессенджер.", "Общение", Color(0xFF229ED9)),
    AppInfo(2, "YouTube", "Смотрите любимые видео.", "Развлечения", Color(0xFFFF0000)),
    AppInfo(3, "Spotify", "Миллионы треков и подкастов.", "Музыка", Color(0xFF1DB954)),
    AppInfo(4, "Google Maps", "Навигация и карты.", "Навигация", Color(0xFF4285F4)),
    AppInfo(5, "Notion", "Заметки и база знаний.", "Продуктивность", Color(0xFF000000)),
    AppInfo(6, "Figma", "Дизайн и прототипирование.", "Работа", Color(0xFFF24E1E))
)

// Данные для Витрины (ровно 9 штук)
val showcaseApps = listOf(
    AppInfo(10, "VK", "Социальная сеть.", "Общение", Color(0xFF0077FF), true),
    AppInfo(11, "Discord", "Голосовой чат.", "Общение", Color(0xFF5865F2), true),
    AppInfo(12, "Netflix", "Фильмы и сериалы.", "Развлечения", Color(0xFFE50914), true),
    AppInfo(13, "Twitch", "Стримы.", "Развлечения", Color(0xFF9146FF), true),
    AppInfo(14, "Apple Music", "Музыка.", "Музыка", Color(0xFFFA243C), true),
    AppInfo(15, "SoundCloud", "Музыка.", "Музыка", Color(0xFFFF5500), true),
    AppInfo(16, "Yandex Maps", "Навигация.", "Навигация", Color(0xFFFFCC00), true),
    AppInfo(17, "Trello", "Задачи.", "Работа", Color(0xFF0079BF), true),
    AppInfo(18, "Slack", "Рабочий чат.", "Работа", Color(0xFF4A154B), true)
)