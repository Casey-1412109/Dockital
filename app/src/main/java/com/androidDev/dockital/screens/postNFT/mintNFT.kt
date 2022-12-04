package com.androidDev.dockital.screens.postNFT

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.R
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.nftMinter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


@Preview(showBackground = true)
@Composable
fun MintPushPreview(){
    MintPush(
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

@Composable
fun MintPush(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage){
    var buttonEnabled = remember {
        mutableStateOf(true)
    }
    var configurationLocalScreen = LocalConfiguration.current
    var imageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            uri: Uri? -> imageUri.value = uri
    }
    var descriptionRem = remember{
        mutableStateOf("")
    }
    var priceRem = remember{
        mutableStateOf("")
    }
    var nameRem = remember {
        mutableStateOf("")
    }
    var borderStrokeMint = BorderStroke(2.dp, Color.White)
    var scrollState = rememberScrollState()
    var blurValue = 0.dp
    var stroke = Stroke(width = 5f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    LazyColumn (
        modifier = Modifier
            .background(Color(32, 15, 52))
            .fillMaxSize()
            .blur(blurValue)
            .padding(15.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
            ){
        item {
            Spacer(
                modifier = Modifier.padding(top = 11.dp)
            )
            Text(
                text = "Create New NFT",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(
                modifier = Modifier.padding(top = 15.dp)
            )
            Row {
                Text(
                    text = "Image, Video, Audio, or 3D Model",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 15.sp

                )
            }
            Text(
                text = "File Type Supported PNG, JPG, GIF, SVG, MP4",
                color = Color.White,
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.padding(top = 15.dp)
            )
            Box(
                modifier = Modifier
                    .size(
                        width = (configurationLocalScreen.screenWidthDp.dp),
                        height = ((configurationLocalScreen.screenHeightDp * 0.3).dp)
                    )
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    drawRoundRect(
                        color = Color.Green,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx()),
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .fillMaxSize(),
                    onClick = {
                        launcher.launch("image/*")
                    }
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus100),
                            contentDescription = "Plus Icon",
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp),
                        )
                        Text(
                            text = "Click To Add Media",
                            color = Color.White,
                            fontSize = 15.sp
                        )

                    }
                }
                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(context)
                                .data(data = imageUri.value)
                                .build()
                        ),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = " ",
                        modifier = Modifier
                            .clickable {
                                imageUri.value = Uri.EMPTY
                                launcher.launch("image/*")
                            }
                            .fillMaxSize()
                    )
                }
            }
            Spacer(
                modifier = Modifier.padding(top = 15.dp)
            )
            Row {
                Text(
                    text = "Name ",
                    color = Color.White,
                    fontSize = 15.sp
                )
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 15.sp,

                    )

            }
            Spacer(
                modifier = Modifier.padding(top = 3.dp)
            )
            TextField(
                value = nameRem.value,
                onValueChange = {
                    nameRem.value = it
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
                modifier = Modifier

                    .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.padding(top = 15.dp)
            )
            Row {
                Text(
                    text = "Price ",
                    color = Color.White,
                    fontSize = 15.sp
                )
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 15.sp,

                    )
            }
            Spacer(
                modifier = Modifier.padding(top = 3.dp)
            )
            TextField(
                value = priceRem.value,
                onValueChange = {
                    if (it.isEmpty() || it.matches(Regex("^\\d+\$"))) {
                        priceRem.value = it
                    }
                },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
                modifier = Modifier
                    .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "Description ",
                color = Color.White,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.padding(top = 3.dp)
            )
            TextField(
                value = descriptionRem.value,
                onValueChange = {
                    descriptionRem.value = it
                },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
                modifier = Modifier
                    .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(140.dp)

            )
            Spacer(
                modifier = Modifier.padding(start = 15.dp)
            )
            Button(
                enabled = buttonEnabled.value,
                onClick =  {
//                    if(
//                        nameRem.value.isEmpty() ||
//                        Uri.EMPTY.equals(imageUri) ||
//                        priceRem.value.isEmpty()
//                    ){
//                        Toast.makeText(context, "Fill All Fields", Toast.LENGTH_SHORT).show()
//                    }
//                    else{
                        nftMinter(
                            context = context,
                            dbConnect = dbConnect,
                            uri = imageUri.value,
                            name = nameRem.value,
                            price = priceRem.value,
                            description = descriptionRem.value,
                            dbStorageConnect = dbStorageConnect
                        )
//                    }

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(84,180,53)),
                border = borderStrokeMint,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Mint",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                modifier = Modifier.padding((configurationLocalScreen.screenHeightDp * 0.04).dp)
            )
        }
    }
}