package com.androidDev.dockital

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.MacAddress
import android.net.wifi.WifiManager
import android.provider.Settings.Secure
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.navigations.BottomBar
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.onBoarding.OnBoardingScreen
import com.androidDev.dockital.screens.searchNav.mainViewModel
import com.androidDev.dockital.screens.searchNav.SearchScreen
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.screens.logReg.LoginScreen
import com.androidDev.dockital.screens.postNFT.MintPush
import com.androidDev.dockital.screens.profileNav.MainProfile
import com.androidDev.dockital.screens.statsNav.StatsScreen
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen(mainViewModel: mainViewModel, context: Context, dbConnect: FirebaseDatabase) {
    val navigationController = rememberNavController()
    var tempAuth : Boolean = true
    Scaffold(
        bottomBar = {
            if (!tempAuth) {
                BottomBar(navController = navigationController)
            }

        }
    ) {
        NavHost(navController = navigationController,
            startDestination =if (tempAuth) {
                NavigationItem.Login.route
            } else {
                NavigationItem.Home.route
            }) {
//            composable(NavigationItem.Login.route) {
//                OnBoardingScreen(
//                    navigateAction = {
//                        navigationController.navigate(NavigationItem.Home.route)
//                    }
//                )
//            }

            composable(NavigationItem.Home.route) {
                HomeScreen()
            }

            composable(NavigationItem.Stats.route) {
                StatsScreen()
            }

            composable(NavigationItem.Add.route) {
                MintPush(context = context, dbConnect = dbConnect)
            }

            composable(NavigationItem.Search.route) {
                //Text("Search")
                SearchScreen()
            }

            composable(NavigationItem.Profile.route) {
                MainProfile(navController = navigationController)
            }
            composable(NavigationItem.Login.route) {
                LoginScreen(dbConnect = dbConnect, context = context)
            }
        }

    }
}

@Preview
@Composable
fun RootScreenPreview() {
    NFTMarketplaceTheme {
        RootScreen(mainViewModel = mainViewModel(), context = MainActivity(), dbConnect =  FirebaseDatabase.getInstance())
    }
}