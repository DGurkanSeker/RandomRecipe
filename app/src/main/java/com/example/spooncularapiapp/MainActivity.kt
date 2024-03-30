package com.example.spooncularapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spooncularapiapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiKey = "bde1fa2d95e1437fb07808ff9bf13ac4"
val apiUrl = "https://api.spoonacular.com/"
lateinit var binding: ActivityMainBinding
class MainActivity :AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.suggestButton.setOnClickListener {
            getRandomRecipe()
        }
}

private fun getRandomRecipe() {
    val retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val recipeService = retrofit.create(RecipeService::class.java)

    val call = recipeService.getRandomRecipe(apiKey)
    call.enqueue(object : Callback<RecipeResponse> {
        override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
            if (response.isSuccessful) {
                val recipeResponse = response.body()
                recipeResponse?.let { showRecipeDetails(it) }
            } else {
                // Hata durumlarını işleyin
            }
        }

        override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
            // Ağ hatasını işleyin
        }
    })
}

private fun showRecipeDetails(recipeResponse: RecipeResponse) {
    val recipe = recipeResponse.recipes.firstOrNull()
    recipe?.let {
        binding.mutfak.setText(recipe.foodName)
        binding.malzeme.setText(recipe.foodIngredients)
        binding.tarif.setText(recipe.foodrec)
    }
}
}