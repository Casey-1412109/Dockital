package com.androidDev.dockital.screens.searchNav

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.R
import com.androidDev.dockital.models.Ranking
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.ui.theme.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LinePlot
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import java.util.*
import kotlin.random.Random


@Composable
fun DetailScreen(nftName:String?,context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage,mainActivity: MainActivity) {
    var scaffoldState: ScaffoldState = rememberScaffoldState()
    var nft =  Ranking("Azumi", R.drawable.ranking01, "Ujjwal", "Dhruv", 3.99, 200055.02)
    for( i in rankings){
        if (i.title.lowercase() == nftName?.lowercase()){
            nft = i
        }
    }
    Scaffold(
        modifier = Modifier
            .background(Color(33, 17, 52))
            .padding(10.dp),
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .fillMaxWidth()
                    .background(Color(33, 17, 52)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Green,
                    )
                }
                IconButton(
                    onClick = {}
                ){ Text(text = nft.title, style = MaterialTheme.typography.h5, color = Color.Green) }
                IconButton(onClick = {})
                {
                    Icon(
                        imageVector = Icons.Filled.IosShare,
                        contentDescription = null,
                        tint = Color.Green,
                    )
                }
            }
        }
    ) {
            Content(nft,
                modifier = Modifier
                    .padding(it)
                    .background(
                        Color(33, 17, 52)
                    ),
                navController = navController,
                mainActivity = mainActivity
            )

    }
}

@Composable
private fun Content(nft:Ranking,modifier: Modifier, navController: NavController,mainActivity: MainActivity) {
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(
            4.dp
        )) {
        Spacer(modifier = Modifier.size(7.dp))
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
        Spacer(modifier = Modifier.size(12.dp))
        Product(nft)
        //FollowerCard(i = 1, padding = 0.dp)
        Spacer(modifier = Modifier.size(24.dp))
        Column(modifier = Modifier
            .background(Color.White.copy(0.15f))
            .padding(8.dp))
        {

            NameRow(nft)
            Spacer(modifier = Modifier.size(24.dp))
            val date="1.1.22"
            Text(
                text = "Amet minim mollit non deserunt ullamco est\n" +
                        "sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
                        + "sit aliqua dolor do amet sint. Velit officia consequat duis enim velit molli " +
                        "sit aliqua dolor do amet sint. Velit officia consequat duis enim velit molli sit" +
                        " aliqua dolor do amet sint. Velit officia consequat duis enim velit molli",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier.padding(7.dp)
            )
            Text(
                text = "Minted at : ${nft.eth} \t  On : $date",
                style = MaterialTheme.typography.h6,
                color = Color.Yellow,
                modifier = Modifier.padding(7.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Price History",
                style = MaterialTheme.typography.h4,
                color = Color.White,
            )
            Spacer(modifier = Modifier.size(10.dp))
            //priceHistory()
            SampleLineGraph(lines = listOf(DataPoints.dataPoints1))
            Spacer(modifier = Modifier.size(20.dp))
            val ctx = LocalContext.current
            val activity = (LocalContext.current as? Activity)
            val context = LocalContext.current
            var intent = context.packageManager
                .getLaunchIntentForPackage("io.metamask")
            var packageName = "io.metamask";
           // val inten = context.packageManager.getLaunchIntentForPackage("com.application.zomato")
            if (intent == null) {
                intent = try {
                    // if play store installed, open play store, else open browser
                    Intent(Intent.ACTION_VIEW, Uri.parse("android-app://$packageName"))
                } catch (e: java.lang.Exception) {
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                }
            }
            Button(
                onClick = {
                    val c: Date = Calendar.getInstance().time
                    val df = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault())
                    val transcId: String = df.format(c)

                    makePayment(
                        "1.0",
                        "abhaydeepsharma61@oksbi",
                        "Abhay",
                        "Test",
                                transcId,
                        ctx,
                        activity!!,
                        mainActivity
                    )

                    /// MetaMask
//                    if (intent != null) {
//                        startActivity(context, intent, null)
//                    }
//                    else{
//                        Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show()
//                    }


                          //////////// navController
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 56.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Buy ", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp)
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
        }
    }
}

private fun makePayment(
    amount: String,
    upi: String,
    name: String,
    desc: String,
    transcId: String, ctx: Context, activity: Activity, mainActivity: PaymentStatusListener
) {
    try {
        // START PAYMENT INITIALIZATION
        val easyUpiPayment = EasyUpiPayment(activity) {
            this.paymentApp = PaymentApp.ALL
            this.payeeVpa = upi
            this.payeeName = name
            this.transactionId = transcId
            this.transactionRefId = transcId
            this.payeeMerchantCode = transcId
            this.description = desc
            this.amount = amount
        }
        // END INITIALIZATION

        // Register Listener for Events
        easyUpiPayment.setPaymentStatusListener(mainActivity)

        // Start payment / transaction
        easyUpiPayment.startPayment()
    } catch (e: Exception) {
        // on below line we are handling exception.
        e.printStackTrace()
        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
    }
}



@OptIn(ExperimentalTextApi::class)
@Composable
private fun NameRow(nft:Ranking) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = nft.title, style = MaterialTheme.typography.h4,color = Color.Green)
        Spacer(modifier = Modifier.size(1.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.icon_eth) ,
                contentDescription = null,
                modifier = Modifier.size(30.dp,30.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = nft.eth.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Green
            )
        }
    }
}

@Composable
fun Product(nft:Ranking) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 375.dp)
            .border(1.dp, Color.Cyan, RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = nft.image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(350.dp, 350.dp)
        )
    }
}


@Composable
fun SampleLineGraph(lines: List<List<DataPoint>>) {
    LineG(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(color = Color.Green),
                    LinePlot.Intersection(color = Color.Green),
                    LinePlot.Highlight(color = Color.Yellow),
                )
            ),
            grid = LinePlot.Grid(Color.Green, steps = 4),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    )
}

@Preview
@Composable
fun DetailsScreenPrev() {
    NFTMarketplaceTheme {
        DetailScreen(
            "Azumi",
            context = MainActivity().context,
            navController = rememberNavController(),
            dbConnect = FirebaseDatabase.getInstance(),
            localStorageRef = MainActivity().getSharedPreferences(
                " ",
                Context.MODE_PRIVATE
            ),
            dbStorageConnect = FirebaseStorage.getInstance(),
            mainActivity = MainActivity()
        )
    }
}

