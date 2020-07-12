package com.coronatracker.coronatracker.model

class User {

    private var name = ""
    private var state = ""
    private var city = ""
    private var contactNo = ""
    private var aadharNo = ""



    fun getName(): String {
        return name
    }

    fun setName(nameValue: String) {
        this.name = nameValue
    }

    fun getState(): String {
        return state
    }

    fun setState(stateValue: String) {
        this.state = stateValue
    }

    fun getCity(): String {
        return city
    }

    fun setCity(cityValue: String) {
        this.city = cityValue
    }

    fun getContactNo(): String {
        return contactNo
    }

    fun setContactNo(contactNoValue: String) {
        this.contactNo = contactNoValue
    }

    fun getAadharNo(): String {
        return aadharNo
    }

    fun setAadharNo(aadharNoValue: String) {
        this.aadharNo = aadharNoValue
    }

}