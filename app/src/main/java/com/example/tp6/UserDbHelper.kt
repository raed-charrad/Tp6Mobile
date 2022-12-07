package com.example.tp6

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper (context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    public val db = writableDatabase
    companion object {
        val DATABASE_NAME = "user.db"
        val DATABASE_VERSION = 1
        val SQL_CREATE_ENTRIES = "CREATE TABLE " + DbContract.UserEntry.TABLE_NAME + " (" +
                DbContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.UserEntry.COLUMN_NOM + " TEXT," +
                DbContract.UserEntry.COLUMN_PRENOM + " TEXT," +
                DbContract.UserEntry.COLUMN_LOGIN + " TEXT," +
                DbContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                DbContract.UserEntry.COLUMN_TEL + " TEXT," +
                DbContract.UserEntry.COLUMN_MDP + " TEXT)"
        public val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $DbContract.UserEntry.TABLE_NAME"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertUser(user: UserModel) {
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
    }

    @SuppressLint("Range")
    fun getAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val cursor = db.rawQuery("SELECT * FROM ${DbContract.UserEntry.TABLE_NAME}", null)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val nom = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_NOM))
                val prenom = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_PRENOM))
                val login = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_LOGIN))
                val email = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_EMAIL))
                val tel = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_TEL))
                val mdp = cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_MDP))
                users.add(UserModel(nom, prenom, login, email, tel, mdp))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return users
    }
}