package com.androidDev.dockital.models

import com.androidDev.dockital.R
import java.util.*

data class Ranking(
    val title: String,
    val image: Int,
    val creator: String,
    val owner: String,
    var percentChange: Double = 0.0,
    var eth: Double = 0.0,
    val id: UUID = UUID.randomUUID()
)

var rankings = listOf<Ranking>(
    Ranking("Azumi", R.drawable.ranking01, "Ujjwal", "Dhruv", 3.99, 200055.02),
    Ranking("Hape prime", R.drawable.ranking02, "Ujjwal", "Dhruv", 33.79, 180055.45),
    Ranking("Cryoto", R.drawable.ranking03, "Ujjwal", "Dhruv", -6.56, 90055.62),
    Ranking("Ape Club", R.drawable.ranking04, "Ujjwal", "Dhruv", 3.99, 88055.12),
    Ranking("Bat", R.drawable.ranking05, "Ujjwal", "Dhruv", 3.99, 10055.06),
    Ranking("Mutant", R.drawable.ranking06, "Ujjwal", "Dhruv", 3.99, 9095.27),
    Ranking("Metaverse", R.drawable.ranking07, "Ujjwal", "Dhruv", -3.53, 2342.4),
    Ranking("Mountain", R.drawable.ranking08, "Ujjwal", "Dhruv", 5.23, 2349024.53),
    Ranking("Mutant Ape", R.drawable.ranking05, "Ujjwal", "Dhruv", -23.4, 93492.3),
    Ranking("The Mountain", R.drawable.ranking10, "Ujjwal", "Dhruv", 302.3, 239802.3)
)