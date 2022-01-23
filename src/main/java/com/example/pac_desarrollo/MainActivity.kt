package com.example.pac_desarrollo

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


//Main
class MainActivity : AppCompatActivity() {
    //iniciamos media player
    lateinit var mediaplayer: MediaPlayer

    //Función de creación de la activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pasamos la canción a la variable mediaplayer
        mediaplayer= MediaPlayer.create(this, R.raw.musica)




        //Botón play y pause.
        play_btn.setOnClickListener {
            if(!mediaplayer.isPlaying){
                mediaplayer.start()

            }
            pause_btn.setOnClickListener {
                mediaplayer.pause()

            }
        }

        //vamos a la activity 2
        button1.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            mediaplayer.stop();
            startActivity(intent)
            Toast.makeText(applicationContext, "Estás en la activity 2", Toast.LENGTH_SHORT).show()
        }

        //vamos a la activity 3
        button2.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            mediaplayer.stop();//detenemos la música
            startActivity(intent)
        }

        //vamos a la activity 4.
        button3.setOnClickListener{
            val intent = Intent(this, MainActivity4::class.java)
            mediaplayer.stop();
            startActivity(intent)
        }

    }

    //Al minimizar la app paramos la música
    override fun onStop() {
        super.onStop()
        mediaplayer.pause()
    }

    //Al volver a la app, vuelve la música
    override fun onRestart() {
        super.onRestart()
        mediaplayer.start()
    }

    //Si cerramos la app, paramos la música
    override fun onDestroy() {
        super.onDestroy()
        mediaplayer.stop()
    }
}

