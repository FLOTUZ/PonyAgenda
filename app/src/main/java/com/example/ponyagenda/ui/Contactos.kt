package com.example.ponyagenda.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        val arr = ArrayList<ContactItem>()
        arr.add(ContactItem(1,"Emmanuel","flotuz10@gmail.com", "4433110399"))

        binding.lvContacts.adapter = ContactsAdapter(view.context, R.layout.contacto_item, arr)
    }

    fun String.toast() {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }
}