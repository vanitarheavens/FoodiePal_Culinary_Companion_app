package vanitar.mdp.foodiepal_culinary_companion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealPlannerAdapter(private var meals: List<Meal>) :
    RecyclerView.Adapter<MealPlannerAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    fun updateData(newMeals: List<Meal>) {
        meals = newMeals
        notifyDataSetChanged()
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textMealName)
        private val dateTextView: TextView = itemView.findViewById(R.id.textMealDate)

        fun bind(meal: Meal) {
            nameTextView.text = meal.name
            dateTextView.text = meal.date
            // Add other bindings as needed
        }
    }
}
