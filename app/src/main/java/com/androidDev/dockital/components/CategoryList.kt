package com.androidDev.dockital.components

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.models.categories
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

@Composable
fun CategoryList(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage) {
    LazyRow(
        modifier = Modifier.padding(vertical = 30.dp).clickable {
            navController.navigate(NavigationItem.Stats.route)
        },
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->
            CategoryCardScreen(
                title = category.title,
                image = painterResource(id = category.image),
                context = context,
                navController = navController,
                dbConnect = dbConnect,
                localStorageRef = localStorageRef,
                dbStorageConnect = dbStorageConnect
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoryList() {
    NFTMarketplaceTheme {
        CategoryList(
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