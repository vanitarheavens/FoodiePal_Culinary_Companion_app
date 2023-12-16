package vanitar.mdp.foodiepal_culinary_companion

// Create RecipeManager singleton object
object RecipeManager {
    // List to hold recipes
    private val recipeList: MutableList<Recipe> = mutableListOf()

    // Function for adding a new recipe to the list
    fun addRecipe(recipe: Recipe) {
        recipeList.add(recipe)
    }

    // Function for getting the list of recipes
    fun getRecipes(): List<Recipe> {
        return recipeList.toList()
    }

}