package com.example.pac_desarrollo

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {


    private val REQUEST_CAMERA = 500 //le damos un id al permiso de la cámara


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //Nos aseguramos que al abrir la cámara la música haya sido detenida
        openCamera_click()
        val mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.musica)
        mediaplayer.stop()

        //vamos a la activity 1
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //Abrimos la cámara
    private fun openCamera(){
            //variable de la imagen
            val value = ContentValues()
            //intent de sacar la foto
            val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //iniciamos la cámara si tiene los permisos
            startActivityForResult(camaraIntent, REQUEST_CAMERA)
        }


    private fun openCamera_click(){
            imageView.setOnClickListener(){
                //miramos la versión de la app y si es mayor o menor de la 6.0 ya que antes de esta los permisos se conceden automaticamente
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //si los permisos no están concedidos entonces
                    if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                        //se piden permisos al user
                        val permisosCamara = arrayOf(android.Manifest.permission.CAMERA)
                        requestPermissions(permisosCamara, REQUEST_CAMERA)
                        //sino se abre la cámara
                    }else{
                        openCamera()
                    }
                    //si la versión es menor se abre la cámara
                }else{
                    openCamera()
                }
            }
        }

    //Comprobamos los permisos y en caso de no aceptarlos nos lanza un toast de aviso
     override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when(requestCode){
                REQUEST_CAMERA->{
                    //si tenemos el permiso abrimos la cámara
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        openCamera()
                    else
                        //no hay permisos
                        Toast.makeText(applicationContext, "Precisas otorgar permisos para poder abrir la cámara", Toast.LENGTH_SHORT).show()
                }
            }
        }


}