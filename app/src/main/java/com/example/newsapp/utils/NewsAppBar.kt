package com.example.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(title: String, onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 35.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = green),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "menu icon",
                Modifier
                    .padding(start = 12.dp)
                    .size(40.dp)
                    .clickable {
                        onNavigationIconClick()
                    }
            )
        },
        modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
    )

}

@Preview(showBackground = true)
@Composable
fun NewsAppBarPreview() {
    NewsAppBar(title = "News App") {}
}