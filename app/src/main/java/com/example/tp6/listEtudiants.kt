package com.example.tp6

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class listEtudiants : AppCompatActivity() {
    lateinit var listUsers : ArrayList<UserModel>
    lateinit var listeView: ListView
    private var dbHelper: UserDbHelper? = null
    fun getDbHelper(): UserDbHelper? {
        if (dbHelper == null) {
            dbHelper = UserDbHelper(this)
        }
        return dbHelper
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_etudiants)
        listUsers = ArrayList<UserModel>()
        listeView = findViewById(R.id.idlistetu)
        listUsers = getDbHelper()!!.getAllUsers()
        val adapter = CustomAdapter(listUsers)
        listeView.adapter = adapter
    }
}
