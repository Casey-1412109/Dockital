package com.androidDev.dockital.models

import com.androidDev.dockital.R

data class NFTCollection(
    val title: String,
    val image: Int,
    var likes: Int,
)

var collections = mutableListOf<NFTCollection>(
    NFTCollection("3D Art", R.drawable.card_3d, 123),
    NFTCollection("Abstract ART", R.drawable.card_abstract, 200),
    NFTCollection("Portrait Art", R.drawable.card_portrait, 242),
    NFTCollection("Metaverse", R.drawable.card_metaverse, 420)
)