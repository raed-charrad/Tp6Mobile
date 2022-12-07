package com.example.tp6

import android.provider.BaseColumns

object DbContract {
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COLUMN_ID = "id"
            val COLUMN_NOM = "nom"
            val COLUMN_PRENOM = "prenom"
            val COLUMN_LOGIN = "login"
            val COLUMN_EMAIL = "email"
            val COLUMN_TEL = "tel"
            val COLUMN_MDP = "mdp"
        }
    }
}