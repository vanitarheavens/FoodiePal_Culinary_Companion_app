package vanitar.mdp.foodiepal_culinary_companion

object MealManager {
    private val mealList = mutableListOf<Meal>()

    // Get the list of meals
    fun getMeals(): List<Meal> {
        return mealList
    }

    // Add a new meal to the list
    fun addMeal(meal: Meal) {
        mealList.add(meal)
    }
}