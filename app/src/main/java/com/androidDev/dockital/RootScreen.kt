package com.androidDev.dockital

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.navigations.BottomBar
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.onBoarding.OnBoardingScreen
import com.androidDev.dockital.screens.searchNav.mainViewModel
import com.androidDev.dockital.screens.searchNav.SearchScreen
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.screens.postNFT.MintPush
import com.androidDev.dockital.screens.statsNav.StatsScreen

//import com.androidDev.dockital.screens.Search.SearchScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun RootScreen(mainViewModel: mainViewModel) {
    val navigationController = rememberNavController()
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            if (!shouldShowOnBoarding) {
                BottomBar(navController = navigationController)
            }

        }
    ) {
        NavHost(navController = navigationController,
            startDestination = if (shouldShowOnBoarding) {
                NavigationItem.Login.route
            } else {
                NavigationItem.Home.route
            }) {
            composable(NavigationItem.Login.route) {
                OnBoardingScreen(
                    navigateAction = {
                        navigationController.navigate(NavigationItem.Home.route)
                        shouldShowOnBoarding = false
                    }
                )
            }

            composable(NavigationItem.Home.route) {
                HomeScreen()
            }

            composable(NavigationItem.Stats.route) {
                StatsScreen()
            }

            composable(NavigationItem.Add.route) {
                MintPush()
            }

            composable(NavigationItem.Search.route) {
                //Text("Search")
                SearchScreen()
            }

            composable(NavigationItem.Profile.route) {
                Text("Profile")
            }
        }

    }
}

//@Preview
//@Composable
//fun RootScreenPreview() {
//    NFTMarketplaceTheme {
//        RootScreen(mainViewModel = main)
//    }
//}