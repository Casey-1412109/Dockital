package com.androidDev.dockital

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

class MainActivity : ComponentActivity() {

    private val mainViewModel: mainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NFTMarketplaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}
