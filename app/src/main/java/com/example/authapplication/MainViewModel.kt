package com.example.authapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    /************************LOGIN ONLY****************************/

    // login password
    val passwordEdit = MutableLiveData<String>("")

    // login email editing
    val emailEdit = MutableLiveData<String>("")
    val emailError = MutableLiveData(false)


    fun validEmail() {
        emailError.value = !emailEdit.value!!.isValidEmail()
        registerEmailError.value = !registerEmailEdit.value!!.isValidEmail()
    }
    fun login() : Boolean {
        return emailEdit.value == "abc@gmail.com"
    }

    fun emailErrorValue() : Boolean {
        return emailError.value!!
    }

    /************************LOGIN ONLY****************************/
    val registerFullName = MutableLiveData("")
    var registerFullNameError = MutableLiveData(false)


    /************************REGISTER ONLY****************************/

    //register password
    val registerPasswordEdit = MutableLiveData<String>("")


    //register username
    val registerUsernameEdit = MutableLiveData("")
    private val usernameEditError = MutableLiveData(false)

    // register email editing
    val registerEmailEdit = MutableLiveData<String>("")
    val registerEmailError = MutableLiveData(false)

    fun registerEmailErrorValue() : Boolean {
        return registerEmailError.value!!
    }


    // username
    fun usernameVerify() : Boolean {
        return usernameEditError.value!!
    }

    fun fullNameVerify() : Boolean {
        return registerFullNameError.value!!
    }
    /************************REGISTER ONLY****************************/

}