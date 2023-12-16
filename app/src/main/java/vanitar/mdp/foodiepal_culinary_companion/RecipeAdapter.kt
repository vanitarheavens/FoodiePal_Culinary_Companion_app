package vanitar.mdp.foodiepal_culinary_companion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes.toList()  // Create a new instance of the list
        notifyDataSetChanged()  // Notify the adapter that the data set has changed
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textRecipeName)
        private val ingredientsTextView: TextView = itemView.findViewById(R.id.textIngredients)
        private val instructionsTextView: TextView = itemView.findViewById(R.id.textInstructions)

        fun bind(recipe: Recipe) {
            nameTextView.text = recipe.name
            ingredientsTextView.text = recipe.ingredients
            instructionsTextView.text = recipe.instructions
        }
    }
}
