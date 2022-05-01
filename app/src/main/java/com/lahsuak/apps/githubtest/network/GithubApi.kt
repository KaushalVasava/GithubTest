package com.lahsuak.apps.githubtest.network

import com.lahsuak.apps.githubtest.model.Item
import com.lahsuak.apps.githubtest.utils.Constants
import retrofit2.http.*


interface GithubApi {

    companion object{
        var BASE_URL = Constants.apiURL
    }

    @Headers("Accept: application/json")
    @GET("/search/repositories")
    suspend fun getSearchResult(
        @Query("q") query: String,
        @Query("") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort:String,
        @Query("order") order: String
    ): Item

}