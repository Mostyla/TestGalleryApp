package xyz.lckyswmrs.testgalleryapp.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

const val PREFERENCES_NAME = "app_preferences"

class AppPreferences(context: Context) {

    private var mPrefs: SharedPreferences? = null
    private val sharedPreferencesName = PREFERENCES_NAME

    init {
        mPrefs = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }

    fun setSavedImage(imgNameKey: String, isSaveValue: Boolean) {
        mPrefs?.edit {
            putBoolean(imgNameKey, isSaveValue)
        }
    }

    fun getSavedImage(imgNameKey: String): Boolean {
        return mPrefs?.getBoolean(imgNameKey, false)!!
    }
}
