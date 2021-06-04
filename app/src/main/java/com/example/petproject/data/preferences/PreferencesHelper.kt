package com.example.petproject.data.preferences

import android.content.Context
import android.util.Log
import com.example.petproject.data.model.Notification
import com.example.petproject.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext val context: Context) {

    val prefs = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)


    fun setNotifications(id: Int, settings: Notification) {
        prefs.edit().putString(id.toString(), Json.encodeToString(Notification.serializer(), settings)).apply()
        Log.d("tag", this.toString())
    }

    fun getNotifications(id: Int) = prefs.getString(id.toString(), "")

}