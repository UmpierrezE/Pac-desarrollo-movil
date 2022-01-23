package com.example.uf2_bdsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.pac_desarrollo.Database
import com.example.pac_desarrollo.MainActivity2


const val DATABASE_NAME = "Tareas"
const val TABLE_NAME = "lista_tareas"
const val COL_ID =" id"
const val COL_NOMBRE = "Tarea"
const val COL_DESCRIPCION = "Descripcion"
const val COL_ESTADO = "Estado"

class AdminSQLiteOpenHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    //Creación por defecto de la base de datos
    override fun onCreate(db: SQLiteDatabase) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE + " VARCHAR(256)," +
                COL_DESCRIPCION +" VARCHAR(256)," +
                COL_ESTADO + " VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //Función para ir agregando los datos a la bd
    fun insertData(database :Database){

        val db = this.writableDatabase
                                            //Iniciamos la base de datos y el content value para poder agregar los datos
        var cv = ContentValues()



        cv.put(COL_NOMBRE, database.nombre)
        cv.put(COL_DESCRIPCION, database.descripcion) // Le pasamos nombre, descripción y estado.
        cv.put(COL_ESTADO, database.estado)



        var resultado = db.insert(TABLE_NAME, null, cv)  //Hacemos el insert de los datos y verificamos el valor devuelto.
        if(resultado == -1.toLong())//si el resultado es -1 es porque hay un error, en ese caso mandamos un toast.
            Toast.makeText(context, "Ha ocurrido un error, verifique los datos e intente nuevamente.", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Tabla actualizada.", Toast.LENGTH_SHORT).show()//si no hay errores, enviamos que la tabla se ha actualizado
    }



    //funcion para leer los datos
    fun readData() : MutableList<Database> {

        var lista: MutableList<Database> = ArrayList()//creamos un array para guardalos


        val db = this.readableDatabase

        val query = "Select * from " + TABLE_NAME //select

        val resultado = db.rawQuery(query,null)

        if(resultado.moveToFirst()){
            do {

                var datos = Database()
                datos.id = resultado.getString(0).toInt()
                datos.nombre = resultado.getString(1)
                datos.descripcion = resultado.getString(2)          //metemos los datos en el array
                datos.estado = resultado.getString(3)
                lista.add(datos)

            } while (resultado.moveToNext()) //con el cursor recorremos TODOS los datos
        }

        //cerramos flujos
        resultado.close()
        db.close()

        return lista    //devolvemos la lista
    }
}