package com.example.newsapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsappc39_g_sat.model.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(newsData: ArticlesItem) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        GlideImage(
            model = newsData.urlToImage,
            contentDescription = "",
            // contentScale = ContentScale.FillBounds,

            modifier = Modifier
                .fillMaxWidth()
                //.aspectRatio(2F) // Width = 2*Height
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = newsData.source?.name ?: "",
            style = TextStyle(color = Color.Gray, fontSize = 10.sp)
        )
        Text(
            text = newsData.title ?: "",
            style = TextStyle(color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        )
        Text(
            text = newsData.publishedAt ?: "",
            style = TextStyle(color = Color.Gray, fontSize = 10.sp),
            modifier = Modifier.align(Alignment.End)
        )

    }
}


