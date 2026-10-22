package com.example.education.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import com.example.education.R
import com.example.education.models.AppDetails
import com.example.education.models.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsContent(
    app: AppDetails,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Информация о приложении")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Иконка, название и категория
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = app.iconUrl,
                    contentDescription = app.name,
                    placeholder = painterResource(
                        R.drawable.ic_launcher_foreground
                    ),
                    error = painterResource(
                        R.drawable.ic_launcher_background
                    ),
                    modifier = Modifier
                        .size(88.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = app.name,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = app.developer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = getCategoryText(app.category),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Дополнительные характеристики
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${app.ageRating}+",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Возраст",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${app.size} МБ",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Размер",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            HorizontalDivider()

            // Скриншоты, если они имеются
            if (!app.screenshotUrlList.isNullOrEmpty()) {
                Text(
                    text = "Скриншоты",
                    style = MaterialTheme.typography.titleLarge
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(app.screenshotUrlList) { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = "Скриншот приложения",
                            modifier = Modifier
                                .width(240.dp)
                                .height(140.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                HorizontalDivider()
            }

            // Описание приложения
            Text(
                text = "Описание",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider()

            // Разработчик
            Text(
                text = "Разработчик",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = app.developer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider()

            // Твоя готовая витрина
            ShowCase(
                apps = apps,
                onAppClick = onShowcaseAppClick
            )
        }
    }
}

@Composable
private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложения"
    Category.GAME -> "Игры"
    Category.PRODUCTIVITY -> "Производительность"
    Category.SOCIAL -> "Социальные сети"
    Category.EDUCATION -> "Образование"
    Category.ENTERTAINMENT -> "Развлечения"
    Category.MUSIC -> "Музыка"
    Category.VIDEO -> "Видео"
    Category.PHOTOGRAPHY -> "Фотография"
    Category.HEALTH -> "Здоровье"
    Category.SPORTS -> "Спорт"
    Category.NEWS -> "Новости"
    Category.BOOKS -> "Книги"
    Category.BUSINESS -> "Бизнес"
    Category.FINANCE -> "Финансы"
    Category.TRAVEL -> "Путешествия"
    Category.MAPS -> "Карты"
    Category.FOOD -> "Еда"
    Category.SHOPPING -> "Покупки"
    Category.UTILITIES -> "Утилиты"
}