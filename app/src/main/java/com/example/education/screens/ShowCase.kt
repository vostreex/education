package com.example.education.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.education.models.AppDetails
import com.example.education.models.Category
import com.example.education.ui.theme.EducationTheme
import kotlinx.coroutines.launch

@Composable
fun ShowCase(
    apps: List<AppDetails>,
    onAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier
){
    val pages = apps.take(9).chunked(3)

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Предыдущая страница"
                )
            }
            Text(
                text = "Страница ${pagerState.currentPage+1} из ${pages.size}"
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                },
                enabled = pagerState.currentPage < pages.lastIndex
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Предыдущая страница"
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 8.dp
        ) { page ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pages[page].forEach {
                    AppListItem(
                        app = it,
                        onAppClick = {
                            onAppClick(it)
                        },
                    )
                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
private fun ShowcasePreview() {
    val previewApps = List(9) { index ->
        AppDetails(
            id = index.toString(),
            name = "Приложение ${index + 1}",
            developer = "VK",
            category = Category.SOCIAL,
            ageRating = 12,
            size = 85.5f,
            iconUrl = "",
            screenshotUrlList = null,
            description = "Краткое описание приложения ${index + 1}"
        )
    }

    EducationTheme {
        ShowCase(
            apps = previewApps,
            onAppClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

