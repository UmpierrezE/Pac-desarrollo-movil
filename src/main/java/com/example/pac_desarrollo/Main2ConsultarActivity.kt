package com.example.pac_desarrollo

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.uf2_bdsqlite.AdminSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_main2_consultar.*
import kotlinx.android.synthetic.main.activity_main2_consultar.titulo
import kotlinx.android.synthetic.main.activity_main2_consultar.volver
import kotlinx.android.synthetic.main.activity_main2_insert.*
import org.w3c.dom.Text

//activity para consultar los datos
class Main2ConsultarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_consultar)
        val context = this
        titulo.setText(AdminSQLiteOpenHelper(context).databaseName)//pasamos el título
        titulo.paintFlags = titulo.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG

        var baseDatos = AdminSQLiteOpenHelper(context)
        var data = baseDatos.readData()//leemos los datos

        //con un búcle rellenamos los datos y le damos estilo
        for(i in 0..(data.size - 1)){
            var tbrow :TableRow = TableRow(this)
            var t1v :TextView = TextView(this)
            var t2v :TextView = TextView(this)
            var t3v :TextView = TextView(this)
            var t4v :TextView = TextView(this)

            t1v.setText(data.get(i).id.toString())
            t1v.setTextColor(Color.WHITE)
            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)

            t2v.setText(data.get(i).nombre.capitalize())
            t2v.setTextColor(Color.WHITE)
            t2v.gravity = Gravity.CENTER
            tbrow.addView(t2v)

            t3v.setText(data.get(i).descripcion.capitalize())
            t3v.setTextColor(Color.WHITE)
            t3v.gravity = Gravity.CENTER
            tbrow.addView(t3v)

            t4v.setText(data.get(i).estado.toUpperCase())
            t4v.setTextColor(Color.WHITE)
            t4v.gravity = Gravity.CENTER
            tbrow.addView(t4v)

            tabla.addView(tbrow)

        }

        volver.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }


}