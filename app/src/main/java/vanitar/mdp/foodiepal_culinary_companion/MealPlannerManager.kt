package vanitar.mdp.foodiepal_culinary_companion

object MealPlannerManager {
    private val mealList = mutableListOf<Meal>()

    fun getMeals(): List<Meal> {
        return mealList
    }

    fun addMeal(meal: Meal) {
        mealList.add(meal)
    }

    // Add other methods as needed
}
