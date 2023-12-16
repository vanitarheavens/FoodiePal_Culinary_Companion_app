package vanitar.mdp.foodiepal_culinary_companion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AboutMeFragment : Fragment() {

    private lateinit var ovalImageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewBiography: TextView
    private lateinit var fabAddAboutMe: FloatingActionButton

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the image picker launcher
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result of the image picker
                val selectedImageUri = result.data?.data
                ovalImageView.setImageURI(selectedImageUri)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        ovalImageView = view.findViewById(R.id.ovalImageView)
        textViewName = view.findViewById(R.id.textViewName)
        textViewBiography = view.findViewById(R.id.textViewCulinaryJourney)

        ovalImageView.setOnClickListener {
            // Show image picker dialog when the oval image is clicked
            showImagePickerDialog()
        }

        // Set click listener for adding details using the parent FloatingActionButton
        fabAddAboutMe = view.findViewById(R.id.fabAddAboutMe)
        fabAddAboutMe.setOnClickListener {
            // Show the add details dialog
            showAddDetailsDialog()
        }

        // Retrieve and display saved details
        val (name, biography) = SharedPreferencesManager.getDetails(requireContext())
        updateDetailsViews(name, biography)

        return view
    }

    private fun showImagePickerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_image_picker, null)
        val btnChooseFromGallery = dialogView.findViewById<Button>(R.id.btnChooseFromGallery)
        val btnTakePhoto = dialogView.findViewById<Button>(R.id.btnTakePhoto)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose Image")
            .setView(dialogView)
            .create()

        btnChooseFromGallery.setOnClickListener {
            // Open gallery to choose an image
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePickerLauncher.launch(intent)
            dialog.dismiss()
        }

        btnTakePhoto.setOnClickListener {
            // Open camera to take a photo (you can implement this part)
            // For simplicity, we are not implementing it here.
            showToast("Take Photo feature not implemented.")
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun showAddDetailsDialog() {
        // Inflate the dialog view
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_details, null)

        // Find the views in the dialog view
        val etName = dialogView.findViewById<EditText>(R.id.etName)
        val etBiography = dialogView.findViewById<EditText>(R.id.etBiography)
        val btnAddDetails = dialogView.findViewById<Button>(R.id.btnAddDetails)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        // Retrieve and display the current biography
        val currentBiography = textViewBiography.text.toString()
        etBiography.setText(currentBiography)

        // Build the dialog
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Edit Details")
            .setView(dialogView)

        // Show the dialog
        val dialog = builder.create()
        dialog.show()

        // Set click listener for the Add Details button
        btnAddDetails.setOnClickListener {
            // Retrieve the updated biography from the EditText field
            val updatedName = etName.text.toString()
            val updatedBiography = etBiography.text.toString()

            // Save the updated biography
            SharedPreferencesManager.saveDetails(requireContext(), updatedName, updatedBiography)

            // Update UI
            updateDetailsViews(updatedName, updatedBiography)

            // Dismiss the dialog
            dialog.dismiss()
        }

        // Set click listener for the Cancel button
        btnCancel.setOnClickListener {
            // Dismiss the dialog
            dialog.dismiss()
        }
    }

    private fun updateDetailsViews(name: String?, biography: String?) {
        // Update TextViews with retrieved details
        textViewName.text = name.takeIf { it?.isNotBlank() == true } ?: "Your Name"

        textViewBiography.text = biography ?: "Culinary Journey Details"
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
