package com.androidDev.dockital

import  android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidDev.dockital.navigations.BottomBar
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.screens.searchNav.SearchScreen
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.screens.logReg.LoginScreen
import com.androidDev.dockital.screens.postNFT.MintPush
import com.androidDev.dockital.screens.profileNav.MainProfile
import com.androidDev.dockital.screens.searchNav.DetailScreen
import com.androidDev.dockital.screens.statsNav.StatsScreen
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


@Preview
@Composable
fun RootScreenPreview() {
    NFTMarketplaceTheme {
        RootScreen(
            context = MainActivity(),
            dbConnect =  FirebaseDatabase.getInstance(),
            dbStorageConnect = FirebaseStorage.getInstance(),
            localStorageRef = MainActivity().getSharedPreferences("", Context.MODE_PRIVATE),
            mainActivity = MainActivity()
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
    localStorageRef: SharedPreferences,
    mainActivity: MainActivity
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
            composable(
                "details/{NftName}",
                arguments = listOf(navArgument("NftName") { type = NavType.StringType })
            ) {
                    backStackEntry ->
                backStackEntry.arguments?.getString("NftName")?.let { nftName ->
//                DetailsScreen(nftName = nftName, navControllerDetails = {
//                    navController.navigate("main")
//                })
                    DetailScreen(
                        nftName = nftName,
                        context = context,
                        navController = navController,
                        dbConnect = dbConnect,
                        localStorageRef = localStorageRef,
                        dbStorageConnect = dbStorageConnect,
                        mainActivity = mainActivity
                    )
                }
            }
            composable("mainStateScreen") {
                StatsScreen(
                    context = context,
                    navController = navController,
                    dbConnect = dbConnect,
                    localStorageRef = localStorageRef,
                    dbStorageConnect = dbStorageConnect
                )
            }
            composable("detailedMainStates/{NftName}",
                arguments = listOf(navArgument("NftName"){defaultValue = "Azumi"})
            )
            {backStackEntry ->
                val nftName = backStackEntry.arguments?.getString("NftName")
                nftName?.let {
                    DetailScreen(
                        nftName = it,
                        context = context,
                        navController = navController,
                        dbConnect = dbConnect,
                        localStorageRef = localStorageRef,
                        dbStorageConnect = dbStorageConnect,
                        mainActivity = mainActivity
                    )
                }
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