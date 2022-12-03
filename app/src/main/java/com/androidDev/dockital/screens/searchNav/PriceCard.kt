package com.androidDev.dockital.screens.searchNav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidDev.dockital.R
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme


@Composable
fun CategoryPriceScreen(title: String, price : Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(27.dp))
            .border(
                width = 1.dp,
                color = Color.White.copy(0.5f),
                shape = RoundedCornerShape(27.dp)
            )
            .background(Color.White.copy(0.15f),)
            .height(100.dp)
            .width(180.dp)
    ) {
        Text(
            title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 10.dp, start = 10.dp)

        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.icon_eth),
                contentDescription = null,
                Modifier
                    .size(25.dp)
                    .padding(top = 5.dp)
            )
            Text(
                price.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewPriceCard() {
    NFTMarketplaceTheme {
        Column() {
            CategoryPriceScreen(
                title = "10.12.22",
                price = 100
            )
            CategoryPriceScreen(
                title = "20.12.22",
                price = 200
            )
            CategoryPriceScreen(
                title = "30.12.22",
                price = 300
            )
        }
    }
}


