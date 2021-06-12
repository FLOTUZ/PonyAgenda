package com.example.ponyagenda.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {

    private val variable = MutableLiveData<String>()

    val getVariable: LiveData<String> get() = variable

    fun addVariable(v:String){
        variable.value = v
    }
}