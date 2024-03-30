package com.example.spooncularapiapp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/random")
    fun getRandomRecipe(@Query("apiKey") apiKey : String): Call<RecipeResponse>
}