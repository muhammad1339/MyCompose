package com.example.mycompose.ui.main.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormValidationVM : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    companion object {
        const val TAG = "FormValidationVM"
    }

    val isFormEmailValid = MediatorLiveData<Boolean>().apply {
        addSource(email) { field ->
            value = isEmailValid(field)
            Log.d(TAG, ": $field --> $value")
        }
    }
    val isFormPasswordValid = MediatorLiveData<Boolean>().apply {

        addSource(password) { field ->
            value = isPasswordValid(field)
            Log.d(TAG, ": $field --> $value")
        }
    }

    private fun isEmailValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }
}