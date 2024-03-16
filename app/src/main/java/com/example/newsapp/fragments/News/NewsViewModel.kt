package com.example.newsapp.fragments.News

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiManager
import com.route.newsappc39_g_sat.model.ArticlesItem
import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.model.SourcesItem
import com.route.newsappc39_g_sat.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// memory leak        // lifecycle Aware -> destroy for activity means destroy for view model also
class NewsViewModel : ViewModel() {
    // jetpack compose state + UI Logic and API calls and database calls
    val selectedIndexState = mutableIntStateOf(0)
    val newsSources = mutableStateListOf<SourcesItem>()
    val newsStatesItems = mutableStateListOf<ArticlesItem>()
    fun fetchSources(categoryId: String) {


        //ApiManager.getNewsServices().getNewsSources(Constants.API_KEY, "general","en")
        ApiManager.getNewsServices().getNewsSources(Constants.API_KEY, categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        newsSources.addAll(sources)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }

            })
    }

    fun fetchNewsBySource(sourceId: String) {
        ApiManager.getNewsServices()
            .getNewsArticles(Constants.API_KEY, sourceId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    newsStatesItems.clear()
                    val newsList = response.body()?.articles
                    if (newsList?.isNotEmpty() == true) {
                        newsStatesItems.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {

                }

            })
    }
}