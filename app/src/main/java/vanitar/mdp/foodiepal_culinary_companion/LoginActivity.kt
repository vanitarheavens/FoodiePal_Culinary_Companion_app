package vanitar.mdp.foodiepal_culinary_companion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)

        registerButton.setOnClickListener {
            // Perform registration
            handleRegistration()
        }

        loginButton.setOnClickListener {
            // Perform login
            handleLogin()
        }
    }

    private fun handleRegistration() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (isUsernameValid(username) && isPasswordValid(password)) {

            // Save the username using SharedPreferences (for demonstration purposes)
            saveCredentials(username, password)

            showToast("Registration successful! Welcome, $username!")

            // Log in the user after registration
            handleLogin()

            // Clear the username and password fields
            clearInputFields()
        } else {
            showToast("Invalid username or password. Please check your input.")
        }
    }

    private fun handleLogin() {
        val enteredUsername = usernameEditText.text.toString()
        val enteredPassword = passwordEditText.text.toString()

        // Retrieve stored username and password from SharedPreferences
        val storedUsername = getStoredUsername()
        val storedPassword = getStoredPassword()

        // Check if the entered credentials match the stored credentials
        if (enteredUsername == storedUsername && enteredPassword == storedPassword) {
            // Successful login
            showToast("Login successful! Welcome back, $enteredUsername!")

            // Navigate to MainActivity or another activity if needed
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            // Invalid login, clear username and password fields
            clearInputFields()

            showToast("Invalid username or password. Please try again.")
        }
    }

    private fun saveCredentials(username: String, password: String) {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        // In a real app, you should securely store the password, potentially hashed with salt.
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    private fun getStoredUsername(): String? {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    private fun getStoredPassword(): String? {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }

    private fun clearInputFields() {
        usernameEditText.text.clear()
        passwordEditText.text.clear()
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.length >= 4 && !username.matches(Regex("[0-9]+"))
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6 && !(password.matches(Regex("[0-9]+")) || password.matches(Regex("[a-zA-Z]+")))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
    }
}
