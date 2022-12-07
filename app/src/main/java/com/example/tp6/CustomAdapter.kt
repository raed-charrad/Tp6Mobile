package com.example.tp6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter (ListeEtudiants : ArrayList<UserModel>) : BaseAdapter() {
    var ListeEtudiants = ListeEtudiants
    override fun getCount(): Int {
        return ListeEtudiants.size
    }
    override fun getItem(position: Int): Any {
        return ListeEtudiants[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.line, parent, false)
        val nom = view.findViewById<TextView>(R.id.NomEtud)
        val prenom = view.findViewById<TextView>(R.id.Preetud)
        nom.text = ListeEtudiants[position].nom
        prenom.text = ListeEtudiants[position].prenom
        return view
    }
}