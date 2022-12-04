package com.androidDev.dockital.screens.searchNav

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidDev.dockital.MainActivity
import com.androidDev.dockital.models.rankings
import com.androidDev.dockital.navigations.NavigationItem
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen(
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
fun SearchScreen(context : Context, navController: NavController, dbConnect: FirebaseDatabase, localStorageRef: SharedPreferences, dbStorageConnect: FirebaseStorage) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        SearchList(navController = navController, state = textState)
    }
}
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Color(189, 51, 164),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SearchList(navController: NavController, state: MutableState<TextFieldValue>) {
    val countries = rankings
    var filteredCountries: ArrayList<String> = ArrayList()
    for(i in countries) filteredCountries.add(i.title)
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredCountries = if (searchedText.isEmpty()) {
            filteredCountries
        } else {
            val resultList = ArrayList<String>()
            for (search in countries) {
                if (search.title.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(search.title)
                }
            }
            resultList
        }
        items(filteredCountries) { filteredsearch ->
            SearchListItem(
                searchText = filteredsearch,
                onItemClick = { selectedsearch ->
                    navController.navigate("details/$selectedsearch") {
                        popUpTo("main") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun SearchListItem(searchText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(searchText) })
            .background(Color(33, 17, 52))
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = searchText, fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Dockital", fontSize = 18.sp) },
        backgroundColor = Color(33, 17, 52),
        contentColor = Color.White
    )
}