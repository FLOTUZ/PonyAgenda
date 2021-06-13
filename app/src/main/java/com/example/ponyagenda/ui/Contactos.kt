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
import com.example.ponyagenda.adapters.ContactsAdapter
import com.example.ponyagenda.databinding.ContactosFragmentBinding
import com.example.ponyagenda.models.ContactItem
import java.lang.Exception

class Contactos : Fragment() {


    private val viewModel: GlobalViewModel by activityViewModels()

    private var _binding: ContactosFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactosFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MainActivity.daoContacto.consultaContactos().size != 0) {
            viewModel.setListaContactos(MainActivity.daoContacto.consultaContactos())
            viewModel.getListaContactos.observe(viewLifecycleOwner, Observer {
                binding.lvContacts.adapter =
                    ContactsAdapter(view.context, R.layout.contacto_item, it)
            })
        } else {
            binding.lvlListaVacia.visibility = View.VISIBLE
        }

        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_contactos_to_altaContacto)
        }
    }

    fun String.toast() {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }
}