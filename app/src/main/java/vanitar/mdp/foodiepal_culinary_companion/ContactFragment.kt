package vanitar.mdp.foodiepal_culinary_companion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val btnCall: MaterialButton = view.findViewById(R.id.btnCall)
        val btnEmail: MaterialButton = view.findViewById(R.id.btnEmail)

        // Set up click listeners
        btnCall.setOnClickListener {
            dialPhoneNumber("6418192382")
        }

        btnEmail.setOnClickListener {
            composeEmail("vnanyonjo@miu.edu")
        }

        return view
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun composeEmail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$email")
        startActivity(emailIntent)
    }
}
