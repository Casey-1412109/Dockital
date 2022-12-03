package com.androidDev.dockital.screens.searchNav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme


data class NFTPPriceCategory(
    val date: String,
    val price: Int,
)

val categoriesP = listOf<NFTPPriceCategory>(
    NFTPPriceCategory("10.12.22", 100),
    NFTPPriceCategory("20.12.22", 200),
    NFTPPriceCategory("30.12.22", 300)
)

@Composable
fun priceHistory() {
    LazyRow(
        modifier = Modifier.padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categoriesP) { category ->
            CategoryPriceScreen(title = category.date,price = category.price)
        }
    }
}


@Preview
@Composable
fun PreviewPrice() {
    NFTMarketplaceTheme {
        priceHistory()
    }
}
