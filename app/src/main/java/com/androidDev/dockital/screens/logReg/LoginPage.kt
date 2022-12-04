package com.androidDev.dockital.screens.logReg

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.R
import com.androidDev.dockital.logInChecker
import com.androidDev.dockital.navigations.NavigationItem
import com.androidDev.dockital.screens.home.HomeScreen
import com.androidDev.dockital.ui.theme.NFTMarketplaceTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage){
//        if(localStorageRef.all.isEmpty()){
//            localStorageRef.edit().clear().commit()
//            navController.navigate(NavigationItem.Login.route)
//        }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable("LoginScreen") {
            LoginPage(
                context = context,
                navController = navController,
                dbConnect = dbConnect,
                localStorageRef = localStorageRef,
                dbStorageConnect = dbStorageConnect
            )
        }
        composable("registerScreen"
        ) {
            RegisterPage(
                context = context,
                navController = navController,
                dbConnect = dbConnect,
                localStorageRef = localStorageRef,
                dbStorageConnect = dbStorageConnect
            )
        }
        composable("Home"){
            HomeScreen(
                context = context,
                navController = navController,
                dbConnect = dbConnect,
                localStorageRef = localStorageRef,
                dbStorageConnect = dbStorageConnect
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginPage(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage) {
    val image = painterResource(id = R.drawable.logie)
    val userName = remember { mutableStateOf("") }
    val
            passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = image,
                contentDescription = "login_img",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                item {
                    Text(
                        text = "Sign In",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        fontSize = 30.sp,
                        modifier = Modifier.padding(120.dp,0.dp,0.dp,0.dp),
                        color = Color.Gray
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        OutlinedTextField(
                            value = userName.value,
                            onValueChange = { userName.value = it },
                            placeholder = { Text(text = "Email Address",color = Color.Gray) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth((0.8f))
                                .padding(50.dp, 0.dp, 0.dp, 0.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Green,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                            keyboardActions = KeyboardActions { focusRequester.requestFocus() }
                        )
                        OutlinedTextField(
                            value = passwordValue.value,
                            onValueChange = { passwordValue.value = it },
                            placeholder = { Text(text = "Password",color = Color.Gray) },
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
                            modifier = Modifier
                                .fillMaxWidth((0.8f))
                                .padding(50.dp, 0.dp, 0.dp, 0.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Green,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .onFocusEvent {
                                    coroutineScope.launch {
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                },
                            colors = TextFieldDefaults.textFieldColors(textColor = Color.Gray),
                            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation()
                        )

                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(
                            onClick = {
                                if(userName.value == "" || passwordValue.value == "") {
                                    Toast.makeText(
                                        context,
                                        "All Fields are Required",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                else{
                                    logInChecker(
                                        context = context,
                                        dbConnect = dbConnect,
                                        userName = userName.value,
                                        passWord = passwordValue.value,
                                        navController = navController,
                                        localStorageRef = localStorageRef
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp)
                                .padding(50.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Text(
                                text = "Log In",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(70.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }

                        Spacer(modifier = Modifier.padding(20.dp))
                        TextButton(
                            onClick = {
                                navController.navigate("registerScreen")
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White.copy(alpha = 0f))

                        ){
                            Text(
                                text = "Create An Account",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(55.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                    }
                }

            }
        }

    }
}


@Composable
@Preview
fun Login() {
    NFTMarketplaceTheme {
        LoginPage(
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
}