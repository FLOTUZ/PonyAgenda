package com.example.ponyagenda.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ponyagenda.data.DAOContacto
import com.example.ponyagenda.databinding.LlamadasRecientesFragmentBinding

class LlamadasRecientes : Fragment() {

    private val viewModel: GlobalViewModel by activityViewModels()

    private var _binding: LlamadasRecientesFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var daoContacto: DAOContacto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LlamadasRecientesFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        daoContacto = DAOContacto(context,"myfirst_db", null, 1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}