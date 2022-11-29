package com.androidDev.dockital.screens.logReg

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.R
//import com.androidDev.dockital.DataBaseHelper
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme

@Composable
fun RegisterPage(navController: NavController) {
    val ima = painterResource(id = R.drawable.register_page)
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
//    val context: Context = MainActivity.applicationContext()
//    val dbConnect = DataBaseHelper(context = context)
//    val dbReader = dbConnect.readableDatabase
//    val dbWrite = dbConnect.writableDatabase
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
                //.background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = ima,
                contentDescription = "reg_img",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            )
        }


        Box(
            modifier = Modifier
                .padding(20.dp)
               // .background(Color.Transparent),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
                    .clip(RoundedCornerShape(30.dp, 30.dp))
                    //   .background(Color.White.copy(0.15f))
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Sign Up",
                            fontSize = 30.sp,
                            color = Color(235, 235, 245).copy(0.6f),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            )
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            OutlinedTextField(
                                value = nameValue.value,
                                onValueChange = { nameValue.value = it },
                                //label = { Text(text = "Name" , color = Color.Gray) },
                                placeholder = { Text(text = "Name", color = Color.Gray) },
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                            )

                            OutlinedTextField(
                                value = emailValue.value,
                                onValueChange = { emailValue.value = it },
                                //label = { Text(text = "Email Address") },
                                placeholder = { Text(text = "Email Address", color = Color.Gray) },
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                            )

                            OutlinedTextField(
                                value = phoneValue.value,
                                onValueChange = { phoneValue.value = it },
                                //label = { Text(text = "Phone Number") },
                                placeholder = { Text(text = "Phone Number", color = Color.Gray) },
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                            )

                            OutlinedTextField(
                                value = passwordValue.value,
                                onValueChange = { passwordValue.value = it },
                                //label = { Text(text = "Password") },
                                placeholder = { Text(text = "Password", color = Color.Gray) },
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordVisibility.value = !passwordVisibility.value
                                    }) {
                                        Icon(
                                            Icons.Default.Password,
                                            "Password",
                                            tint = if (passwordVisibility.value) Color.Magenta else Color.Blue
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                                else PasswordVisualTransformation()
                            )

                            OutlinedTextField(
                                value = confirmPasswordValue.value,
                                onValueChange = { confirmPasswordValue.value = it },
                                //label = { Text(text = "Confirm Password") },
                                placeholder = { Text(text = "Confirm Password", color = Color.Gray) },
                                singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        confirmPasswordVisibility.value =
                                            !confirmPasswordVisibility.value
                                    }) {
                                        Icon(
                                            Icons.Default.Password,
                                            "Password",
                                            tint = if (passwordVisibility.value) Color.Magenta else Color.Blue
                                        )
                                    }
                                },
                                visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                                else PasswordVisualTransformation()
                            )
                            val localContext = LocalContext.current
                            Spacer(modifier = Modifier.padding(10.dp))
                            Button(
                                onClick ={

                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(50.dp)
                            ) {
                                Text(text = "Sign Up", fontSize = 20.sp)
                            }
                            Spacer(modifier = Modifier.padding(20.dp))
                            TextButton(
                                onClick = {
                                    navController.navigate("LoginScreen")
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0f))

                            ){
                                Text(
                                    text = "Login Instead",
                                    color = Color(235, 235, 245).copy(0.6f),

                                )
                            }
                            Spacer(modifier = Modifier.padding(20.dp))

                        }
                    }
                }
            }
        }
    }
}



@Composable
@Preview
fun Register() {
    NFTMarketplaceTheme {
        val navController = rememberNavController()
        RegisterPage(navController)
    }
}
