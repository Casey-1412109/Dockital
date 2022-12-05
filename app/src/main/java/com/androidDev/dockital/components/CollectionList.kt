package com.androidDev.dockital.components

import android.content.Context
import android.content.SharedPreferences
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
import com.androidDev.dockital.models.collections
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

@Composable
fun CollectionList(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage) {
    LazyRow(
        modifier = Modifier.padding(bottom = 30.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(collections) { collection ->
            CollectionCard(
                title = collection.title,
                image = painterResource(id = collection.image),
                likes = collection.likes,
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
fun PreviewCollectionList() {
    NFTMarketplaceTheme {
        CollectionList(
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