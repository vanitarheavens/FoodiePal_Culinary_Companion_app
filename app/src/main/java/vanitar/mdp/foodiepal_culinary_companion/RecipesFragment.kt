package vanitar.mdp.foodiepal_culinary_companion

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddRecipe: FloatingActionButton
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeAdapter = RecipeAdapter(RecipeManager.getRecipes())
        recyclerView.adapter = recipeAdapter

        fabAddRecipe = view.findViewById(R.id.fabAddRecipe)
        fabAddRecipe.setOnClickListener {
            showAddRecipeDialog()
        }

        return view
    }

    private fun showAddRecipeDialog() {
        val context: Context = requireContext()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add Recipe")

        // Set up the input layout
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val nameEditText = EditText(context)
        nameEditText.hint = "Recipe Name"
        layout.addView(nameEditText)

        val ingredientsEditText = EditText(context)
        ingredientsEditText.hint = "Ingredients"
        layout.addView(ingredientsEditText)

        val instructionsEditText = EditText(context)
        instructionsEditText.hint = "Instructions"
        layout.addView(instructionsEditText)

        builder.setView(layout)

        // Set up the buttons
        builder.setPositiveButton("Add") { _, _ ->
            // Add the recipe with the entered values
            val name = nameEditText.text.toString()
            val ingredients = ingredientsEditText.text.toString()
            val instructions = instructionsEditText.text.toString()

            val newRecipe = Recipe(name, ingredients, instructions)
            RecipeManager.addRecipe(newRecipe)

            // Update the adapter and notify the data set has changed
            recipeAdapter.updateData(RecipeManager.getRecipes())
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}
