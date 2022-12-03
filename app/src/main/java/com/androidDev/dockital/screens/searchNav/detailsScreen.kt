package com.androidDev.dockital.screens.searchNav

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    for( i in rankings){ //////////////// Temporarily Removed For Testing
    if (i.title.lowercase() == nftName?.lowercase()){
        nft = i
    }
}
        var configurationDetails = LocalConfiguration.current
    var scrollStateDetail = rememberScrollState()
    LazyColumn(
        modifier = Modifier
            .background(Color(33, 17, 52))
            .scrollable(state = scrollStateDetail, orientation = Orientation.Vertical)
    )
    {
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .width((configurationDetails.screenWidthDp * 0.5).dp)
                ) {
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
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.IosShare,
                            contentDescription = "Share",
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
                ) {
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
                        text = "${Random.nextInt(1, 30)}",
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
                        .size(400.dp),
                    contentScale = ContentScale.Fit
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
                Card(
                    backgroundColor = Color.White.copy(0.15f),
                    modifier = Modifier.padding(30.dp)
                ) {
                    Text(
                        text = "Name \t : \t ${nft.title} \nCreator \t : \t Ujjwal \n" +
                                "Date of creation \t : \t 20.10.2002 \n" +
                                "Current price \t : \t ${nft.eth} eth  ",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(15.dp)
                    )
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.padding(140.dp, 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White.copy(0.15f),
                        contentColor = Color.White
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = "Buy",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(2.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))

            }
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