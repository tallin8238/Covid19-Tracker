package com.coronatracker.coronatracker.baseclass

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    fun showCustomNotification(
        value: String?
    ) {
        val toast = Toast.makeText(activity, value, Toast.LENGTH_LONG)
        toast.show()
    }

    fun showKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

    }

    fun dismissKeyboard(mInputField: View) {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mInputField.windowToken, 0)
    }
}