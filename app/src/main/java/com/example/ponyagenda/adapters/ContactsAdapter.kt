package com.example.ponyagenda.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ponyagenda.R
import com.example.ponyagenda.models.ContactItem

class ContactsAdapter(val context: Context, val layout: Int, val lista: ArrayList<ContactItem>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Any {
        return lista[position]
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val img = miView.findViewById<ImageView>(R.id.imgContactItem)
        val nombre = miView.findViewById<TextView>(R.id.nombreContactItem)
        val numero = miView.findViewById<TextView>(R.id.numeroContactoItem)
        val email = miView.findViewById<TextView>(R.id.emailContactoItem)

        nombre.text = lista[position].name
        numero.text = lista[position].phone
        email.text = lista[position].email

        return miView
    }
}