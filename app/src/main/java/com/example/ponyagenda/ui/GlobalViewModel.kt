package com.example.ponyagenda.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ponyagenda.models.ContactItem

class GlobalViewModel : ViewModel() {

    private val listaContactos = MutableLiveData<ArrayList<ContactItem>>()
    private val contactoSeleccionado = MutableLiveData<ContactItem>()
    private val llamadasRecientes = MutableLiveData<ArrayList<ContactItem>>()

    val getListaContactos: LiveData<ArrayList<ContactItem>> get() = listaContactos
    val getContacto: LiveData<ContactItem> get() = contactoSeleccionado
    val getLlamadasRecientes:  LiveData<ArrayList<ContactItem>> get() = llamadasRecientes

    fun setListaContactos(contactosList: ArrayList<ContactItem>) {
        listaContactos.value = contactosList
    }

    fun setContactoSeleccionado(contacto: ContactItem) {
        contactoSeleccionado.value = contacto
    }

    fun setLlamadasRecientes(llamadasRecientesList: ArrayList<ContactItem>) {
        llamadasRecientes.value = llamadasRecientesList
    }
}