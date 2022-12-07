package com.example.tp6

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var Nom : EditText
    lateinit var Prenom : EditText
    lateinit var Login : EditText
    lateinit var Email : EditText
    lateinit var Tel : EditText
    lateinit var MDP : EditText
    lateinit var btnValider : Button
    lateinit var btnAnnuler : Button
    lateinit var userDbHelper : UserDbHelper
    lateinit var listUsers : ArrayList<UserModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDbHelper = UserDbHelper(this)
        Nom = findViewById(R.id.NomEtud)
        Prenom = findViewById(R.id.Prenom)
        Login = findViewById(R.id.Login)
        Email = findViewById(R.id.Email)
        Tel = findViewById(R.id.Phone)
        MDP = findViewById(R.id.Mdp)
        btnValider = findViewById(R.id.btnValider)
        btnAnnuler = findViewById(R.id.btnAnnuler)
        // if inputs are empty show dialog box
        btnValider.setOnClickListener() {
            if (Nom.text.toString().isEmpty() || Prenom.text.toString().isEmpty() || Login.text.toString().isEmpty() || Email.text.toString().isEmpty() || Tel.text.toString().isEmpty() || MDP.text.toString().isEmpty()) {
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("Attention")
                dialog.setMessage("Veuillez remplir tous les champs")
                dialog.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                dialog.show()

            }
            else {
                var user = UserModel(Nom.text.toString(), Prenom.text.toString(), Login.text.toString(), Email.text.toString(), Tel.text.toString(), MDP.text.toString())
                userDbHelper.insertUser(user)
                // redirect to list of users
                var intent = Intent(this, listEtudiants::class.java)
                startActivity(intent)

                Nom.text.clear()
                Prenom.text.clear()
                Login.text.clear()
                Email.text.clear()
                Tel.text.clear()
                MDP.text.clear()
            }
        }
        btnAnnuler.setOnClickListener() {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Attention")
            dialog.setMessage("Voulez-vous vraiment remettre à zero tous les champs ?")
            dialog.setPositiveButton("Oui") { dialog, which ->
                dialog.dismiss()
                Nom.text.clear()
                Prenom.text.clear()
                Login.text.clear()
                Email.text.clear()
                Tel.text.clear()
                MDP.text.clear()
            }
            dialog.setNegativeButton("Non") { dialog, which ->
                dialog.dismiss()
            }
            dialog.show()

        }
    }
    /*fun addUser(view: View){
        var user = UserModel(Nom.text.toString(), Prenom.text.toString(), Login.text.toString(), Email.text.toString(), Tel.text.toString(), MDP.text.toString())
        val db = userDbHelper.db
        val values = ContentValues().apply {
            put(DbContract.UserEntry.COLUMN_NOM, user.nom)
            put(DbContract.UserEntry.COLUMN_PRENOM, user.prenom)
            put(DbContract.UserEntry.COLUMN_LOGIN, user.login)
            put(DbContract.UserEntry.COLUMN_EMAIL, user.email)
            put(DbContract.UserEntry.COLUMN_TEL, user.tel)
            put(DbContract.UserEntry.COLUMN_MDP, user.mdp)
        }
        val newRowId = db?.insert(DbContract.UserEntry.TABLE_NAME, null, values)
        println(newRowId)
    }*/
}