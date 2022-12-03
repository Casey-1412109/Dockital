package com.androidDev.dockital

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
//import com.androidDev.dockital.screens.searchNav.mainViewModel
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class MainActivity : ComponentActivity() {
    private lateinit var  localStorageRef :SharedPreferences
    private val dbConnect = FirebaseDatabase.getInstance()
    private val dbStorageConnect = FirebaseStorage.getInstance()
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(context)
        this.localStorageRef = this.context.getSharedPreferences("com.androidDev.dockital", MODE_PRIVATE)
        setContent {
            NFTMarketplaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootScreen(
                        context = context,
                        dbConnect = dbConnect,
                        localStorageRef = localStorageRef,
                        dbStorageConnect = dbStorageConnect
//                        mainViewModel = this.mainViewModel,
//                        context = this.context,
//                        dbConnect = this.dbConnect
                    )

                }
            }
        }
    }
}
