package com.example.pac_desarrollo

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uf2_bdsqlite.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    //Creación de la activity 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val mediaplayer: MediaPlayer = MediaPlayer.create(this,R.raw.musica)
        mediaplayer.stop()

        //vamos a la activity 1
        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            mediaplayer.start();
            startActivity(intent)
        }

        //Si le damos al boton crear, creamos la bd. Si ya existe la borramos y la creamos nuevamente.
        create.setOnClickListener{
            val baseDatos = AdminSQLiteOpenHelper(this)
            val baseDatosNueva = baseDatos.writableDatabase
            val context = this
            baseDatosNueva.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)

            val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                    COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_NOMBRE + " VARCHAR(256)," +
                    COL_DESCRIPCION +" VARCHAR(256)," +
                    COL_ESTADO + " VARCHAR(256))"
            baseDatosNueva.execSQL(createTable)
            //Toast de aviso
            Toast.makeText(context, "Tabla creada", Toast.LENGTH_SHORT).show()
            baseDatos.close()

            }

        //Vamos a la activity para insertar los datos
        insert.setOnClickListener{
            val intent = Intent(this, Main2InsertActivity::class.java)
            startActivity(intent)
        }

        //Vamos a consultar los datos
        consult.setOnClickListener{
            val intent = Intent(this, Main2ConsultarActivity::class.java)
            startActivity(intent)
        }

    }
}
