package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.fragments.CategoriesFragment
import com.example.newsapp.fragments.News.NewsFragmentContent
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.utils.NewsAppBar
import com.example.newsapp.utils.NewsDrawer
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                NewsScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    ModalNavigationDrawer(drawerContent = {
        NewsDrawer(
            onCategoriesClick = {
                navController.popBackStack()
                if (navController.currentDestination?.route != CategoriesFragmentScreen.ROUTE_NAME)
                    navController.navigate(CategoriesFragmentScreen.ROUTE_NAME)
                scope.launch {
                    drawerState.close()
                }
            },
            onSettingsClick = {},
        )
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            NewsAppBar(title = " ") {
                scope.launch {
                    drawerState.open()
                }
            }
        }) { paddingValues ->
//            NewsFragmentContent(Modifier.padding(top = paddingValues.calculateTopPadding()))
            NavHost(
                navController = navController,
                startDestination = CategoriesFragmentScreen.ROUTE_NAME,
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
            ) {
                composable(CategoriesFragmentScreen.ROUTE_NAME) {
                    CategoriesFragment(navHostController = navController)
                }
                composable(
                    "${NewsFragmentScreen.ROUTE_NAME}/{category_id}",
                    arguments = listOf(navArgument("category_id") {
                        type = NavType.StringType
                    })
                ) { navBackStackEntry ->
                    val categoryId = navBackStackEntry.arguments?.getString("category_id")
                    NewsFragmentContent(categoryId = categoryId ?: "")
                }
                composable(SettingsFragmentScreen.ROUTE_NAME) {
                    // SettingsScreen as Composable


                }
            }
        }
    }

}


@Preview
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}