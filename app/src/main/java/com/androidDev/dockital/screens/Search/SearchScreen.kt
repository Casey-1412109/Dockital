package com.androidDev.dockital.screens.Search
//
//import android.annotation.SuppressLint
//import android.icu.text.CaseMap.Title
//import android.service.notification.NotificationListenerService.RankingMap
//import android.util.Log
//import android.widget.SearchView
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.androidDev.dockital.MainViewModel
//import com.androidDev.dockital.R
//import com.androidDev.dockital.SearchWidgetState
//import com.androidDev.dockital.models.Ranking
//import com.androidDev.dockital.models.rankings
//import com.androidDev.dockital.screens.stats.CustomTabComponent
//import java.util.*
//import kotlin.collections.ArrayList
//
//@Composable
//fun SearchScreen(mainViewModel: MainViewModel) {
//    TopBar(mainViewModel = mainViewModel)
//}
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun TopBar(mainViewModel:MainViewModel) {
//    val searchWidgetState by mainViewModel.searchWidgetState
//    val searchTextState by mainViewModel.searchTextState
//
//    Scaffold(
//        topBar = {
//            MainAppBar(
//                searchWidgetState = searchWidgetState,
//                searchTextState = searchTextState,
//                onTextChange = {
//                    mainViewModel.updateSearchTextState(newValue = it)
//                },
//                onCloseClicked = {
//                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
//                },
//                onSearchClicked = {
//                    Log.d("Searched Text", it)
//                    searchClicked(it)
//
//                },
//                onSearchTriggered = {
//                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
//                }
//            )
//        } ,
//                backgroundColor = Color(33, 17, 52)
//    ) {}
//}
//
//@Composable
//fun searchClicked(it:String) {
//    val navController = rememberNavController()
//    val textState = remember { mutableStateOf(TextFieldValue(it)) }
//    SearchList(navController = navController, state = textState)
//}
//
//@Composable
//fun MainAppBar(
//    searchWidgetState: SearchWidgetState,
//    searchTextState: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit,
//    onSearchTriggered: () -> Unit
//) {
//    when (searchWidgetState) {
//        SearchWidgetState.CLOSED -> {
//            DefaultAppBar(
//                onSearchClicked = onSearchTriggered
//            )
//        }
//        SearchWidgetState.OPENED -> {
//            SearchAppBar(
//                text = searchTextState,
//                onTextChange = onTextChange,
//                onCloseClicked = onCloseClicked,
//                onSearchClicked = onSearchClicked
//            )
//        }
//    }
//}
//
//@Composable
//fun DefaultAppBar(onSearchClicked: () -> Unit) {
//    TopAppBar(
//        title = {
//            Text(
//                text = "Home",
//                color = Color.White,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold
//            )
//        },
//        actions = {
//            IconButton(
//                onClick = { onSearchClicked() }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//        },
//        backgroundColor = Color(107,35,142),
//    )
//}
//
//@Composable
//fun SearchAppBar(
//    text: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit,
//) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        elevation = AppBarDefaults.TopAppBarElevation,
//        color = MaterialTheme.colors.primary
//    ) {
//        TextField(modifier = Modifier
//            .fillMaxWidth(),
//            value = text,
//            onValueChange = {
//                onTextChange(it)
//            },
//            placeholder = {
//                Text(
//                    modifier = Modifier
//                        .alpha(ContentAlpha.medium),
//                    text = "Search here...",
//                    color = Color.White
//                )
//            },
//            textStyle = TextStyle(
//                fontSize = MaterialTheme.typography.subtitle1.fontSize
//            ),
//            singleLine = true,
//            leadingIcon = {
//                IconButton(
//                    modifier = Modifier
//                        .alpha(ContentAlpha.medium),
//                    onClick = {}
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        if (text.isNotEmpty()) {
//                            onTextChange("")
//                        } else {
//                            onCloseClicked()
//                        }
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Close Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(
//                onSearch = {
//                    onSearchClicked(text)
//                }
//            ),
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color(107,35,142),
//                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
//            )
//        )
//    }
//}
//
//
//@Composable
//@Preview
//fun DefaultAppBarPreview() {
//    DefaultAppBar(onSearchClicked = {})
//}
//
//@Composable
//@Preview
//fun SearchAppBarPreview() {
//    SearchAppBar(
//        text = "Some random text",
//        onTextChange = {},
//        onCloseClicked = {},
//        onSearchClicked = {}
//    )
//}
//
////jkhjn
//
//@Composable
//fun SearchItem(title: String,
//               onItemClick: (String) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .clickable(onClick = { onItemClick(title) })
//            .background(colorResource(id = android.R.color.holo_purple))
//            .height(57.dp)
//            .fillMaxWidth()
//            .padding(PaddingValues(8.dp, 16.dp))
//    ) {
//        Text(text = title, fontSize = 18.sp, color = Color.White)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchListItemPreview() {
//
//    rankings.forEach {
//        SearchItem(
//            title = it.title,
//            onItemClick = {}
//        )
//    }
//}
//
//@Composable
//fun SearchList(navController: NavController, state: MutableState<TextFieldValue>) {
//    val countries = rankings
//    var filteredCountries = ArrayList<String>()
//    for(i in rankings) filteredCountries.add(i.title)
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//        val searchedText = state.value.text
//        filteredCountries = if (searchedText.isEmpty()) {
//            filteredCountries
//        } else {
//            val resultList = ArrayList<String>()
//            for (country in countries) {
//                if (country.title.lowercase(Locale.getDefault())
//                        .contains(searchedText.lowercase(Locale.getDefault()))
//                ) {
//                    resultList.add(country.title)
//                }
//            }
//            resultList
//        }
//        items(filteredCountries) { filteredCountry ->
//            SearchItem(title = filteredCountry,
//                onItemClick = { selectedCountry ->
//                    navController.navigate("details/$selectedCountry") {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        popUpTo("main") {
//                            saveState = true
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchListPreview() {
//    val navController = rememberNavController()
//    val textState = remember { mutableStateOf(TextFieldValue("")) }
//    SearchList(navController = navController, state = textState)
//}
////
////fun getListOfCountries(): ArrayList<String> {
////    val isoCountryCodes = Locale.getISOCountries()
////    val countryListWithEmojis = ArrayList<String>()
////    for (countryCode in isoCountryCodes) {
////        val locale = Locale("", countryCode)
////        val countryName = locale.displayCountry
////        val flagOffset = 0x1F1E6
////        val asciiOffset = 0x41
////        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
////        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
////        val flag =
////            (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
////        countryListWithEmojis.add("$countryName $flag")
////    }
////    return countryListWithEmojis
////}
//
//@Composable
//fun MainScreen(navController: NavController) {
//    val textState = remember { mutableStateOf(TextFieldValue("")) }
//    Column {
//        SearchView(textState)
//        SearchList(navController = navController, state = textState)
//    }
//}
//
//@Composable
//fun SearchView(state: MutableState<TextFieldValue>) {
//    TextField(
//        value = state.value,
//        onValueChange = { value ->
//            state.value = value
//        },
//        modifier = Modifier
//            .fillMaxWidth(),
//        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
//        leadingIcon = {
//            Icon(
//                Icons.Default.Search,
//                contentDescription = "",
//                modifier = Modifier
//                    .padding(15.dp)
//                    .size(24.dp)
//            )
//        },
//        trailingIcon = {
//            if (state.value != TextFieldValue("")) {
//                IconButton(
//                    onClick = {
//                        state.value =
//                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
//                    }
//                ) {
//                    Icon(
//                        Icons.Default.Close,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(15.dp)
//                            .size(24.dp)
//                    )
//                }
//            }
//        },
//        singleLine = true,
//        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = Color.White,
//            cursorColor = Color.White,
//            leadingIconColor = Color.White,
//            trailingIconColor = Color.White,
//            backgroundColor = Color(188, 33, 75),
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledIndicatorColor = Color.Transparent
//        )
//    )
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun SearchViewPreview() {
//    val textState = remember { mutableStateOf(TextFieldValue("")) }
//    SearchView(textState)
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    val navController = rememberNavController()
//    MainScreen(navController = navController)
//}
//
////error is line 69 @Composable wala



import android.util.Log
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
import com.androidDev.dockital.models.rankings
import java.util.*
import kotlin.collections.ArrayList


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            "details/{countryName}",
            arguments = listOf(navArgument("countryName") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("countryName")?.let { countryName ->
                Log.d("Select",countryName)
                //DetailsScreen(countryName = countryName)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        CountryList(navController = navController, state = textState)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
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

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Composable
fun CountryList(navController: NavController, state: MutableState<TextFieldValue>) {
    val countries = rankings
    var filteredCountries: ArrayList<String> = ArrayList()
    for(i in countries) filteredCountries.add(i.title)
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredCountries = if (searchedText.isEmpty()) {
            filteredCountries
        } else {
            val resultList = ArrayList<String>()
            for (country in countries) {
                if (country.title.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(country.title)
                }
            }
            resultList
        }
        items(filteredCountries) { filteredCountry ->
            CountryListItem(
                countryText = filteredCountry,
                onItemClick = { selectedCountry ->
                    navController.navigate("details/$selectedCountry") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo("main") {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {
    val navController = rememberNavController()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    CountryList(navController = navController, state = textState)
}

@Composable
fun CountryListItem(countryText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(countryText) })
            .background(Color(33, 17, 52))
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = countryText, fontSize = 18.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListItemPreview() {
     //CountryListItem(countryText = "Azumi  \uD83C\uDDEE\uD83C\uDDF3", onItemClick = { })
    // if want the flag
     CountryListItem(countryText = "Azumi", onItemClick = { })
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Dockital", fontSize = 18.sp) },
        backgroundColor = Color(33, 17, 52),
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

