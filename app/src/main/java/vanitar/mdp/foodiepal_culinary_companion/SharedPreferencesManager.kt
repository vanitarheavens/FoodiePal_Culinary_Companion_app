package vanitar.mdp.foodiepal_culinary_companion

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREFERENCE_NAME = "about_me_preferences"
    private const val KEY_NAME = "name"
    private const val KEY_BIOGRAPHY = "biography"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    // Save both name and biography
    fun saveDetails(context: Context, name: String, biography: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_BIOGRAPHY, biography)
        editor.apply()
    }

    // Get both the name and biography
    fun getDetails(context: Context): Pair<String?, String?> {
        val sharedPreferences = getSharedPreferences(context)
        val name = sharedPreferences.getString(KEY_NAME, null)
        val biography = sharedPreferences.getString(KEY_BIOGRAPHY, null)
        return Pair(name, biography)
    }
}
