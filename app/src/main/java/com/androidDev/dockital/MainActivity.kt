package com.androidDev.dockital

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
//import com.androidDev.dockital.screens.searchNav.mainViewModel
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.TransactionDetails


class MainActivity : ComponentActivity() , PaymentStatusListener {
    private lateinit var  localStorageRef :SharedPreferences
    private val dbConnect = FirebaseDatabase.getInstance()
    private val dbStorageConnect = FirebaseStorage.getInstance()
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(context)
        this.localStorageRef = this.context.getSharedPreferences("com.androidDev.dockital", MODE_PRIVATE)
        setContent {
            NFTMarketplaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootScreen(
//                        mainViewModel = this.mainViewModel,
                        context = context,
                        dbConnect = dbConnect,
                        localStorageRef = localStorageRef,
                        dbStorageConnect = dbStorageConnect,
                        mainActivity = this
                    )

                }
            }
        }
    }
    override fun onTransactionCancelled() {
        Toast.makeText(this, "Transaction canceled by user..", Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Toast.makeText(this, "Transaction completed by user..", Toast.LENGTH_SHORT).show()
    }

}
