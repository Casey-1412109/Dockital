package com.androidDev.dockital

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

fun signInChecker(context: Context, dbConnect:FirebaseDatabase, emailId: String?,passWord: String? , navController : NavController){
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        var usernameFetched = it.value as ArrayList<HashMap<String, Int>>
        run breaking@{
            usernameFetched.forEach{ mapUserChecker ->
                var tempKey = mapUserChecker.keys.first().toString()
                if(tempKey == emailId ){
                    dbConnect.getReference("/usersStore/${mapUserChecker[tempKey]}").get().addOnSuccessListener{
                            mapUserFinder ->
                        var passwordFetched:String? = (mapUserFinder.value as HashMap<String, String>)["password"]
                        if(passwordFetched == passWord){
                            navController.navigate("Home")
                        }
                        else{
                            Toast.makeText(context, "Enter Correct Password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    return@forEach
                }
            }
        }
    }.addOnFailureListener {
        Toast.makeText(context, "Unable To Fetch Data", Toast.LENGTH_LONG).show()
    }
}

