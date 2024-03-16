package com.example.newsapp.fragments.News

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.R
import com.example.newsapp.utils.NewsCard
import com.example.newsapp.utils.tabRow
import com.route.newsappc39_g_sat.model.ArticlesItem

@Composable
fun NewsFragmentContent(
    modifier: Modifier = Modifier,
    categoryId: String,
    viewModel: NewsViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
    ) {
        tabRow(categoryId) { sourceId -> viewModel.fetchNewsBySource(sourceId) }
        NewsList(viewModel.newsStatesItems.toList())
    }
}

@Composable
fun NewsList(list: List<ArticlesItem>) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(list.size) { position ->
            NewsCard(list[position])
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFragmentContentPreview() {
    NewsFragmentContent(categoryId = "j")
}