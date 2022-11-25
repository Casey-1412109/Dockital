package com.androidDev.dockital.dataBase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


fun mainDB(){
    Firebase.database.setPersistenceEnabled(true)
}