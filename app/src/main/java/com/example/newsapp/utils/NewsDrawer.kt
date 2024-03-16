package com.example.newsapp.utils

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun NewsDrawer(onCategoriesClick: () -> Unit, onSettingsClick: () -> Unit) {
    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.82F)) {
        Text(
            text = "News App !",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .background(color = green)
                .fillMaxWidth()
                .fillMaxHeight(0.13F)
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        )

        DrawerListIconTitle(R.drawable.categories, "Categories", onItemCLick = {
            onCategoriesClick()
            Log.d("tag", "")
        })
        DrawerListIconTitle(R.drawable.settings, "Settings", onItemCLick = {
            onSettingsClick()
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsDrawerPreview() {
    NewsDrawer(onCategoriesClick = {}, onSettingsClick = {})
}


@Composable
fun DrawerListIconTitle(icon: Int, title: String, onItemCLick: () -> Unit) {

    Row(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onItemCLick()
            }) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp)
                .size(22.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = title,
            style = TextStyle(fontSize = 22.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerListIconTitlePreview() {
    DrawerListIconTitle(R.drawable.categories, "title") {}
}
