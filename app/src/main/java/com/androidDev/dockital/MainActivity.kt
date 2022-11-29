package com.androidDev.dockital

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.androidDev.dockital.screens.searchNav.mainViewModel
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase


class MainActivity : ComponentActivity() {
    private val mainViewModel: mainViewModel by viewModels()
    val context: Context = this
    val dbConnect = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NFTMarketplaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootScreen(mainViewModel = this.mainViewModel,context = this.context , dbConnect = this.dbConnect)
                }
            }
        }
    }
}
