package com.coronatracker.coronatracker.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.preference.PreferenceManager
import java.io.File

class PrefManager(var _context: Context) {
    var pref: SharedPreferences
    var editor: Editor

    // shared pref mode
    private var PRIVATE_MODE = 0

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    companion object {
        // Shared preferences file name
        private const val PREF_NAME = "WaterReminder"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        private var sharedPreferences: SharedPreferences? = null
        fun openPref(context: Context?) {
            sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun getValue(
            context: Context?, key: String,
            defaultValue: String
        ): String{
            try {
                openPref(context)
                val result =
                    sharedPreferences!!.getString(key, defaultValue)
                sharedPreferences = null
                return result.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun setValue(
            context: Context?,
            key: String?,
            value: Int
        ) {
            try {
                openPref(context)
                val prefsPrivateEditor = sharedPreferences!!.edit()
                prefsPrivateEditor!!.putInt(key, value)
                prefsPrivateEditor.apply()
                sharedPreferences = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getValue(
            context: Context?, key: String?,
            defaultValue: Int
        ): Int {
            try {
                openPref(context)
                val result = sharedPreferences!!.getInt(key, defaultValue)
                sharedPreferences = null
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun getValue(
            context: Context?, key: String?,
            defaultValue: Long
        ): Long {
            try {
                openPref(context)
                val result =
                    sharedPreferences!!.getLong(key, defaultValue)
                sharedPreferences = null
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun setValue(
            context: Context,
            key: String,
            value: String
        ) {
            try {
                openPref(context)
                val prefsPrivateEditor = sharedPreferences!!.edit()
                prefsPrivateEditor!!.putString(key, value)
                prefsPrivateEditor.apply()
                sharedPreferences = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getValue(
            context: Context?, key: String?,
            defaultValue: Boolean
        ): Boolean {
            try {
                openPref(context)
                val result =
                    sharedPreferences!!.getBoolean(key, defaultValue)
                sharedPreferences = null
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun setValue(
            context: Context?,
            key: String?,
            value: Boolean
        ) {
            try {
                openPref(context)
                val prefsPrivateEditor = sharedPreferences!!.edit()
                prefsPrivateEditor!!.putBoolean(key, value)
                prefsPrivateEditor.apply()
                sharedPreferences = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getValue(
            context: Context?, key: String?,
            defaultValue: Float
        ): Float {
            try {
                openPref(context)
                val result =
                    sharedPreferences!!.getFloat(key, defaultValue)
                sharedPreferences = null
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defaultValue
        }

        fun setValue(
            context: Context?,
            key: String?,
            value: Float
        ) {
            try {
                openPref(context)
                val prefsPrivateEditor = sharedPreferences!!.edit()
                prefsPrivateEditor.putFloat(key, value)
                prefsPrivateEditor.apply()
                sharedPreferences = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun setValue(
            context: Context?,
            key: String?,
            value: Long
        ) {
            try {
                openPref(context)
                val prefsPrivateEditor = sharedPreferences!!.edit()
                prefsPrivateEditor.putFloat(key, value.toFloat())
                prefsPrivateEditor.apply()
                sharedPreferences = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun deleteAll(context: Context) {
            val preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            deleteCache(context)
        }

        fun deleteCache(context: Context) {
            try {
                val dir = context.cacheDir
                deleteDir(dir)
            } catch (e: Exception) {
            }
        }

        private fun deleteDir(dir: File?): Boolean {
            return if (dir != null && dir.isDirectory) {
                val children = dir.list()
                for (i in children.indices) {
                    val success =
                        deleteDir(File(dir, children[i]))
                    if (!success) {
                        return false
                    }
                }
                dir.delete()
            } else if (dir != null && dir.isFile) {
                dir.delete()
            } else {
                false
            }
        }
    }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        editor = pref.edit()
    }
}