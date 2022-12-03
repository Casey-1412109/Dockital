package com.androidDev.dockital

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class MainActivity : ComponentActivity() {
//    private val mainViewModel: mainViewModel by viewModels()
    private lateinit var  localStorageRef :SharedPreferences
    private val dbConnect = FirebaseDatabase.getInstance()
    private val dbStorageConnect = FirebaseStorage.getInstance()
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(context)
        localStorageRef = this.getSharedPreferences("com.androidDev.dockital", MODE_PRIVATE)
        setContent {
            NFTMarketplaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootScreen(
//                        mainViewModel = this.mainViewModel,
                        context = this.context,
                        dbConnect = this.dbConnect,
                        dbStorageConnect =  this.dbStorageConnect,
                        localStorageRef = this.localStorageRef
                    )
                }
            }
        }
    }
}
