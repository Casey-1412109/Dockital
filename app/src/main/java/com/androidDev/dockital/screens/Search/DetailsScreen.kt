package com.androidDev.dockital.screens.Search

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidDev.dockital.R
import com.androidDev.dockital.models.Ranking
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import kotlin.random.Random


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(nftName:String?,navControllerDetails:() -> Unit)
{

    var nft =  Ranking("Azumi", R.drawable.ranking01, "Ujjwal", "Dhruv", 3.99, 200055.02)
//    for( i:Int = 0 ; i <  rankings.size; i++){ //////////////// Temporarily Removed For Testing
//        if (rankings[i].title.lowercase() == nftName?.lowercase()){
//            nft = i
//        }
//    }
    var configurationDetails = LocalConfiguration.current
    var scrollStateDetail = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color(33, 17, 52))
            .scrollable(state = scrollStateDetail, orientation = Orientation.Vertical)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp)
        ){
            Row(
                modifier = Modifier
                    .width((configurationDetails.screenWidthDp * 0.5).dp)
            ){
                IconButton(
                    onClick = navControllerDetails
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.Green,
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width((configurationDetails.screenWidthDp * 0.5).dp)
            ){
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.IosShare,
                        contentDescription = "Back",
                        tint = Color.Green,
                    )
                }
            }

        }
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .height(0.75.dp)
                .width((configurationDetails.screenWidthDp).dp)
        )
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    end = 15.dp
                )
        ) {
            Text(
                text = nft.creator,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Green,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = nft.owner,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Green,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = " #",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Green,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = "${Random.nextInt(1, 50)}",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Green,
                    textAlign = TextAlign.Left
                )
            }
            Image(
                painter = painterResource(id = nft.image),
                contentDescription = "rank1",
                modifier = Modifier
                    .padding(20.dp, 20.dp)
                    .size(400.dp)
            )
            Text(
                text = nft.title,
                fontSize = 43.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Green,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()


            )
        }

    }


}

@Preview
@Composable
fun DetailsScreenPreview() {
    NFTMarketplaceTheme {
        DetailsScreen("Azumi", {})
    }
}