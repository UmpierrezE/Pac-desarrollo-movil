package com.example.pac_desarrollo

import android.database.sqlite.SQLiteDatabase
import java.io.FileDescriptor

class Database {

    //Estructura de la bd
    var id : Int = 0
    var nombre : String = ""
    var descripcion : String = ""
    var estado : String = ""
    //constructor de la bd
    constructor(nombre: String, descripcion: String, estado: String){
        this.nombre = nombre
        this.descripcion = descripcion
        this.estado = estado
    }

    constructor(){

    }


}