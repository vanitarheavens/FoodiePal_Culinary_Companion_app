package vanitar.mdp.foodiepal_culinary_companion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vanitar.mdp.foodiepal_culinary_companion.databinding.FragmentMealPlannerBinding
import java.text.SimpleDateFormat
import java.util.*
class MealPlannerFragment : Fragment() {
    private lateinit var binding: FragmentMealPlannerBinding
    private lateinit var fabAddMeal: FloatingActionButton
    private lateinit var mealAdapter: MealPlannerAdapter
    private val datePicker: MaterialDatePicker<Long> by lazy {
        MaterialDatePicker.Builder.datePicker().build()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Initialize RecyclerView
        mealAdapter = MealPlannerAdapter(MealManager.getMeals())
        binding.recyclerViewMeals.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mealAdapter
        }
// Initialize FloatingActionButton
        fabAddMeal = binding.fabAddMeal
        fabAddMeal.setOnClickListener {
// Show the add meal dialog
            showAddMealDialog()
        }
    }
    private var isDatePickerAdded = false
    private fun showAddMealDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_meal, null, false)
        val etMealName = dialogView.findViewById<EditText>(R.id.etMealName)
        val etDate = dialogView.findViewById<EditText>(R.id.etDate)
// Check if the MaterialDatePicker is already added
        if (!isDatePickerAdded) {
            etDate.setOnClickListener {
// Show MaterialDatePicker
                datePicker.show(parentFragmentManager, "MaterialDatePicker")
            }
            isDatePickerAdded = true
        }
        datePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            etDate.setText(dateFormat.format(Date(it)))
        }
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Add Meal")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val mealName = etMealName.text.toString()
                val date = etDate.text.toString()
                if (mealName.isNotEmpty() && date.isNotEmpty()) {
                    val newMeal = Meal(mealName, date)
                    MealManager.addMeal(newMeal)
                    mealAdapter.updateData(MealManager.getMeals())
                } else {
                    Toast.makeText(requireContext(), "Please Enter data in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
        alertDialogBuilder.show()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
// Save fragment state if needed
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
// Restore fragment state if needed
    }
}

