package com.example.ponyagenda.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
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
        val card = miView.findViewById<CardView>(R.id.cardContactItem)
        val layActions = miView.findViewById<LinearLayout>(R.id.layActions)
        val nombre = miView.findViewById<TextView>(R.id.nombreContactItem)
        val numero = miView.findViewById<TextView>(R.id.numeroContactoItem)
        val email = miView.findViewById<TextView>(R.id.emailContactoItem)

        nombre.text = lista[position].name
        numero.text = lista[position].phone
        email.text = lista[position].email

        //Para controlar el IF
        var visible = false
        //Se setea el estado por defecto, del layout de acciones
        layActions.visibility = View.INVISIBLE
        card.setOnClickListener {
            if (visible){
                layActions.visibility = View.INVISIBLE
                visible = false
            }else{
                layActions.visibility = View.VISIBLE
                visible =true
            }
        }

        return miView
    }
}