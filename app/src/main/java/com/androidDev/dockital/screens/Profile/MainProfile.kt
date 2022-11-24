package com.androidDev.dockital.screens.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun mainProfile(navController: NavController){
    var configurationProfile = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(32, 15, 52))
    ) {
        Box(
                                   modifier= Modifier
                .fillMaxWidth()
                .height((configurationProfile.screenHeightDp * 0.2).dp)
                .background(Color.White)

        ){
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ){

            }
        }

    }
}


@Preview
@Composable
fun mainProfilePreview(){
    mainProfile(navController = rememberNavController())
}