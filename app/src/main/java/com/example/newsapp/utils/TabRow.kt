package com.example.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.fragments.News.NewsViewModel
import com.example.newsapp.ui.theme.green

@Composable
fun tabRow(
    categoryId: String,
    viewModel: NewsViewModel = viewModel(),
    onTabSelected: (sourceId: String) -> Unit
//    onNewsSourcesTabSelectedListener: OnNewsSourcesTabSelectedListener
) {
    //launched effect to run one time until the key changed
    LaunchedEffect(Unit) {
        viewModel.fetchSources(categoryId)
    }

    ScrollableTabRow(
        selectedTabIndex = viewModel.selectedIndexState.intValue,
        divider = {},
        indicator = {},
        edgePadding = 10.dp
    )
    {
        viewModel.newsSources.forEachIndexed { index, item ->

            LaunchedEffect(Unit) {
                if (viewModel.newsSources.isNotEmpty())
                    onTabSelected(viewModel.newsSources[0].id ?: "")
            }

            Tab(
                selected = viewModel.selectedIndexState.intValue == index,
                onClick = {
                    viewModel.selectedIndexState.intValue = index
                    onTabSelected(item.id ?: "")
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green
            )
            {
                Text(
                    text = item.name ?: "BBC",
                    modifier = if (viewModel.selectedIndexState.intValue == index)
                        Modifier
                            .padding(horizontal = 5.dp, vertical = 12.dp)
                            .background(green, CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    else
                        Modifier
                            .padding(horizontal = 5.dp, vertical = 12.dp)
                            .border(2.dp, green, CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}




