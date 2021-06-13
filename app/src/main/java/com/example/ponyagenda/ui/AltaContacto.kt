package com.example.ponyagenda.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ponyagenda.MainActivity
import com.example.ponyagenda.databinding.AltaContactoFragmentBinding
import com.example.ponyagenda.models.ContactItem

@Suppress("SENSELESS_COMPARISON")
class AltaContacto : Fragment() {

    private val viewModel: GlobalViewModel by activityViewModels()

    private var _binding: AltaContactoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AltaContactoFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener {
            val img = binding.imgAddContacto
            val nombre = binding.nombreAddContacto.text.toString()
            val telefono = binding.telefonoAddContacto.text.toString()
            val email = binding.emailAddContacto.text.toString()

            //Alta de contacto
            if (nombre.isNotEmpty() && telefono.isNotEmpty() && email.isNotEmpty()) {
                val contacto = ContactItem(0, nombre, telefono, email)
                MainActivity.daoContacto.altaContacto(contacto)
                "Contacto agregado con exito!".toast()
                activity?.onBackPressed();
            } else {
                "Verifique que los datos estan completos".toast()
            }
        }

    }

    fun String.toast() {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }

}