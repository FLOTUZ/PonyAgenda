package com.example.ponyagenda.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ponyagenda.MainActivity
import com.example.ponyagenda.R
import com.example.ponyagenda.databinding.EditarContactoFragmentBinding
import com.example.ponyagenda.models.ContactItem
import java.lang.Exception

class EditarContacto : Fragment() {

    private val viewModel: GlobalViewModel by activityViewModels()

    private var _binding: EditarContactoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditarContactoFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getContacto.observe(viewLifecycleOwner, Observer {
            //binding.imgEditContacto.setText(it.img)
            binding.nombreEditContacto.setText(it.name)
            binding.telefonoEditContacto.setText(it.phone)
            binding.emailEditContacto.setText(it.email)
        })

        binding.btnGuardarEditContacto.setOnClickListener {

            //val img = binding.imgEditContacto
            val nombre = binding.nombreEditContacto.text.toString()
            val telefono = binding.telefonoEditContacto.text.toString()
            val email = binding.emailEditContacto.text.toString()

            if (nombre != "" && telefono != "" && email != "") {
                viewModel.getContacto.observe(viewLifecycleOwner, Observer {
                    val viejo = it
                    val nuevo = ContactItem(0, nombre, email, telefono)
                    try {
                        MainActivity.daoContacto.editarContacto(viejo, nuevo)
                        "Contacto editado con exito".toast()
                        viewModel.setListaContactos(MainActivity.daoContacto.consultaContactos())
                        findNavController().navigate(R.id.action_contactos_to_editarContacto)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        "Opps. Algo sucedio al editar contacto".toast()
                    }
                })
            }
        }

        binding.btnCancelarEditContacto.setOnClickListener {
            //Cerrar el fragment
            activity?.onBackPressed();
        }
    }

    fun String.toast() {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }

}