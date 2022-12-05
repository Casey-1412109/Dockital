package com.androidDev.dockital.screens.statsNav

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.models.Ranking
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


@Composable
fun RankingTable(
    rankings: List<Ranking>,
    context : Context,
    navController: NavController,
    dbConnect: FirebaseDatabase,
    localStorageRef: SharedPreferences,
    dbStorageConnect: FirebaseStorage
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(rankings) { index, ranking ->
            RankingRow(
                index = index,
                title = ranking.title,
                image = ranking.image,
                change = ranking.percentChange,
                eth = ranking.eth,
                navController = navController
            )
        }
    }
}


@Preview
@Composable
fun PreviewRankingTable() {
    NFTMarketplaceTheme {
        RankingTable(
            rankings = rankings,
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