package com.androidDev.dockital.screens.statsNav

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.components.customTabOffset
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.screens.searchNav.DetailScreen
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

@Preview
@Composable
fun StatsScreenPreview(){
    StatsScreen(
        context = MainActivity().context,
        navController = rememberNavController(),
        dbConnect = FirebaseDatabase.getInstance(),
        localStorageRef = MainActivity().getSharedPreferences(
            " ",
            Context.MODE_PRIVATE
        ),
        dbStorageConnect = FirebaseStorage.getInstance()
    )
}
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun StatsScreen(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Column {
                        Text(
                            "Stats",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 30.dp),
                            color = Color.White
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        backgroundColor = Color(33, 17, 52)
    ) {

        Column(
            Modifier.padding(bottom = 30.dp)
        ) {

            CustomTabComponent()

            Card(
                backgroundColor = Color.White.copy(0.15f),
                elevation = 0.dp,
                border = BorderStroke(1.5.dp, Color.White.copy(0.5f)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(16.dp, 20.dp)
            ) {
                RankingTable(rankings, navControllerTable = navController)
            }
        }
    }
}

@Composable
fun CustomTabComponent() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "Ranking" to Icons.Default.Assessment,
        "Activity" to Icons.Default.TrackChanges
    )
    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
                color = Color(66, 34, 104)
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabOffset(tabPositions[tabIndex]),
                height = 4.dp,
                color = Color(148, 103, 255)
            )
        }
    ) {
        tabData.forEachIndexed{ index, pair ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    tabIndex = index
                },
                content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = pair.second, contentDescription = null)
                        Text(
                            pair.first,
 //                           style = NFTTypography.h6
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun StatMainScreenPreview() {
    NFTMarketplaceTheme {
        StatsScreen(
            context = MainActivity().context,
            navController = rememberNavController(),
            dbConnect = FirebaseDatabase.getInstance(),
            localStorageRef = MainActivity().getSharedPreferences(
                " ",
                Context.MODE_PRIVATE
            ),
            dbStorageConnect = FirebaseStorage.getInstance()
        )
    }
}