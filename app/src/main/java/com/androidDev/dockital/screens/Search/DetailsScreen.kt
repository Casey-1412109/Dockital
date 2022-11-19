package com.androidDev.dockital.screens.Search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.R
import com.androidDev.dockital.models.Ranking
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(nftName:String)
{
    var nft =  Ranking("Azumi", R.drawable.ranking01, 3.99, 200055.02)
    for(i in rankings){
        if (i.title.lowercase() == nftName.lowercase()){
            nft = i
        }
    }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Column {
                        Text(
                            nft.title,
                            fontSize = 43.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 120.dp),
                            color = Color.White
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        backgroundColor = Color(33, 17, 52)
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ){

            Image(painter = painterResource(id = nft.image),
                contentDescription = "rank1",
                modifier = Modifier
                    .padding(20.dp, 50.dp)
                    .width(400.dp)
                    .height(400.dp)
            )
            Card(backgroundColor = Color.White.copy(0.15f),
                modifier = Modifier.padding(30.dp)
            ) {
                Text(text = "Name \t : \t ${nft.title} \nCreator \t : \t Ujjwal \n" +
                        "Date of creation \t : \t 20.10.2002 \n" +
                        "Current price \t : \t ${nft.eth} eth  ",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(15.dp)
                )
            }

            OutlinedButton(onClick = { },
                        modifier =Modifier.padding(140.dp,40.dp) ,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White.copy(0.15f),
                            contentColor = Color.White
                        ),
                shape = CircleShape
            ) {
                Text(text = "Buy",
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }


}

@Preview
@Composable
fun DetailsScreenPreview() {
    NFTMarketplaceTheme {
        DetailsScreen("Azumi")
    }
}