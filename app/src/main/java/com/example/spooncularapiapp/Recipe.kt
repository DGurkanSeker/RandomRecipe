package com.example.spooncularapiapp

import com.google.gson.annotations.SerializedName

public data class RecipeResponse(val recipes : List<Recipe>)

data class Recipe(@SerializedName("id") var index : Int,
                  @SerializedName("name") var foodName : String,
                  @SerializedName("ingredients") var foodIngredients : String,
                  @SerializedName("Recipe") var foodrec  : String)
{

}