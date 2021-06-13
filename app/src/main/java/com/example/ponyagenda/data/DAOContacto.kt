package com.example.ponyagenda.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ponyagenda.models.ContactItem
import kotlin.jvm.Throws

class DAOContacto(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        // INT, TEXT, REAL (Float o Double) y BLOB (Bytes)
        val sql =
            "CREATE TABLE contacto(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, telefono TEXT, email TEXT )"

        db?.let {
            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    @Throws
    fun altaContacto(contactItem: ContactItem) {
        val db = writableDatabase

        val sql =
            "INSERT INTO contacto(nombre, telefono, email ) VALUES('${contactItem.name}', '${contactItem.phone}', '${contactItem.email}')"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun contactoByID(id: Int): ContactItem {
        val db = readableDatabase

        val sql = "SELECT * FROM contacto WHERE id=${id} "

        val cursor = db.rawQuery(sql, null)

        val contacto = ContactItem(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        db.close()

        return contacto
    }

    @Throws
    fun consultaContactos(): ArrayList<ContactItem> {
        val db = readableDatabase

        val sql = "SELECT * FROM contacto"

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<ContactItem>()
        while (cursor.moveToNext()) {
            val contact = ContactItem(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )

            resultados.add(contact)
        }
        db.close()

        return resultados
    }

    @Throws
    fun editarContacto(viejo: ContactItem, nuevo: ContactItem) {
        val db = writableDatabase

        val sql =
            "UPDATE contacto SET nombre='${nuevo.name}', telefono='${nuevo.phone}',email='${nuevo.email}' WHERE id=${viejo.id}"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun borrarContacto(contactItem: ContactItem) {
        val db = writableDatabase

        val sql = "DELETE FROM productos WHERE id=${contactItem.id}"

        db.execSQL(sql)

        db.close()
    }
}