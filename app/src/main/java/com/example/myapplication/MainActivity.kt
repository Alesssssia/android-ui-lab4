package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}

// Настройка навигации
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        // Экран списка
        composable("list") {
            AppListScreen(
                apps = mockApps,
                onAppClick = { appId ->
                    navController.navigate("details/$appId")
                }
            )
        }
        // Экран карточки
        composable("details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull() ?: 0
            AppDetailsScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(apps: List<AppInfo>, onAppClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Мои приложения", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Apps,
                            contentDescription = "App Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(apps) { app ->
                AppListItem(app = app, onClick = { onAppClick(app.id) })
            }
        }
    }
}

@Composable
fun AppListItem(app: AppInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }, // Теперь карточка кликабельна
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Верхняя часть: Иконка и текст
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(app.iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = app.name.first().toString(),
                        color = androidx.compose.ui.graphics.Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = app.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(
                        text = app.description,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = app.category,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // НИЖНЯЯ ЧАСТЬ: Блок "Витрина"
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceVariant)

            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Витрина",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 8.dp)
                )

                ShowcasePager(onAppClick = onClick)
            }
        }
    }
}
@Composable
fun ShowcasePager(onAppClick: () -> Unit) {
    // Разбиваем 9 приложений на 3 страницы по 3 штуки
    val pages = showcaseApps.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 16.dp
    ) { page ->
        // На каждой странице рисуем колонку с тремя приложениями
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            pages[page].forEach { app ->
                MiniAppItem(app = app, onClick = onAppClick)
            }
        }
    }
}

// Маленькая вёрстка для элементов витрины (кликабельная)
@Composable
fun MiniAppItem(app: AppInfo, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Клик по элементу витрины
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(app.iconColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = app.name.first().toString(),
                color = androidx.compose.ui.graphics.Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = app.name, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Text(text = app.category, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}