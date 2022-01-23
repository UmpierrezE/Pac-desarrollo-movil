package com.example.pac_desarrollo

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uf2_bdsqlite.AdminSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_main2_insert.*

class Main2InsertActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_insert)
        val context = this
        setContentView(R.layout.activity_main2_insert)
        titulo.setText(AdminSQLiteOpenHelper(context).databaseName)
        titulo.paintFlags = titulo.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG

        insertar.setOnClickListener {
            if ((nombre.text.toString().length > 0) && (descripcion.text.toString().length > 0) && (estado.text.toString().length > 0))
            {
                var baseDatos = Database(  //si la longitud de los campos es mayor que 0 pasamos los datos a string
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    estado.text.toString()
                )
                var db = AdminSQLiteOpenHelper(context)
                db.insertData(baseDatos)// y lo insertamos
            }else{
                Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()//Si alguno está a 0, enviamos un toast
            }
        }

        volver.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }


}