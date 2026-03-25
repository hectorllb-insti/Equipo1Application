package com.hectorllb.equipo1application.controllers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseController(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_NAME = "AppDatabase.db"
            private const val DATABASE_VERSION = 1

            // Tabla y columnas
            const val TABLE_USERS = "users"
            const val COLUMN_ID = "id"
            const val COLUMN_USER = "user"
            const val COLUMN_PASS = "pass"
        }

        override fun onCreate(db: SQLiteDatabase) {
            // Creación de tabla estableciendo cada usuario como único
            val createTable = ("CREATE TABLE $TABLE_USERS ("
                    + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "$COLUMN_USER TEXT UNIQUE,"
                    + "$COLUMN_PASS TEXT)")
            db.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
            onCreate(db)
        }

        // Función para registrar un usuario
        fun registerUser(user: String, pass: String): Boolean {
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_USER, user)
                put(COLUMN_PASS, pass)
            }

            // insert() devuelve -1 si falla (por ejemplo, si el usuario ya existe)
            val result = db.insert(TABLE_USERS, null, values)
            db.close()
            return result != -1L
        }

        // Función para comprobar el login
        fun checkUser(user: String, pass: String): Boolean {
            val db = this.readableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USER = ? AND $COLUMN_PASS = ?",
                arrayOf(user, pass)
            )
            val exists = cursor.count > 0
            cursor.close()
            db.close()
            return exists
        }
    }