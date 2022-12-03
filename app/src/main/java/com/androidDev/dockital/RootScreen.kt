package com.androidDev.dockital

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.navigations.BottomBar
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.screens.searchNav.SearchScreen
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.screens.logReg.LoginScreen
import com.androidDev.dockital.screens.postNFT.MintPush
import com.androidDev.dockital.screens.profileNav.MainProfile
import com.androidDev.dockital.screens.statsNav.StatsScreen
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


@Preview
@Composable
fun RootScreenPreview() {
    NFTMarketplaceTheme {
        RootScreen(
//            mainViewModel = mainViewModel(),
            context = MainActivity(),
            dbConnect =  FirebaseDatabase.getInstance(),
            dbStorageConnect = FirebaseStorage.getInstance(),
            localStorageRef = MainActivity().getSharedPreferences("", Context.MODE_PRIVATE)
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen(
//    mainViewModel: mainViewModel,
    context: Context,
    dbConnect: FirebaseDatabase,
    dbStorageConnect: FirebaseStorage,
    localStorageRef: SharedPreferences
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if(localStorageRef.all.isNotEmpty()){
                BottomBar(navController = navController)
            }

        }
    ) {
        NavHost(navController = navController,
            startDestination =if (localStorageRef.all.isEmpty()) {
                NavigationItem.Login.route
            } else {
                NavigationItem.Home.route
            }) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable(NavigationItem.Stats.route) {
                StatsScreen(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable(NavigationItem.Add.route) {
                MintPush(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable(NavigationItem.Search.route) {
                SearchScreen(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable(NavigationItem.Profile.route) {
                MainProfile(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable(NavigationItem.Login.route) {
                LoginScreen(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }

        }

    }
}