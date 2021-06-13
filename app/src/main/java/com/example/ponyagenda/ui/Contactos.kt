package com.example.ponyagenda.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ponyagenda.MainActivity
import com.example.ponyagenda.R
import com.example.ponyagenda.adapters.ContactsAdapter
import com.example.ponyagenda.databinding.ContactosFragmentBinding
import com.example.ponyagenda.models.ContactItem

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
                binding.lvContacts.adapter = object :
                    ContactsAdapter(view.context, R.layout.contacto_item, it) {
                    override fun smsAcontacto(contactItem: ContactItem) {
                        val num: String = String.format("smsto:${contactItem.phone}")
                        val smsIntent = Intent(Intent.ACTION_SENDTO)
                        smsIntent.data = Uri.parse(num)
                        ContextCompat.startActivity(context, smsIntent, null)
                    }

                    override fun llamarContacto(contactItem: ContactItem) {
                        val tel: String = String.format("tel: ${contactItem.phone}")
                        val call = Intent(Intent.ACTION_DIAL)
                        call.data = Uri.parse(tel)
                        ContextCompat.startActivity(context, call, null)
                    }

                    override fun editContacto(contactItem: ContactItem) {
                        viewModel.setContactoSeleccionado(contactItem)
                        findNavController().navigate(R.id.action_contactos_to_editarContacto)
                    }

                    override fun deleteContacto(contactItem: ContactItem) {
                        MainActivity.daoContacto.borrarContacto(contactItem)
                        viewModel.setListaContactos(it)
                        onViewCreated(view, savedInstanceState)
                    }

                    override fun sendMail(contactItem: ContactItem) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        val data =
                            Uri.parse("mailto:${contactItem.email}?subject=Mensaje de ${contactItem.name}")
                        intent.data = data
                        startActivity(intent)
                    }

                }
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