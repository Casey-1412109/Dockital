package com.androidDev.dockital

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase


fun userStoreDataGen(name: String, emailId: String , phoneNumber: String, passWord: String): HashMap<String, Any?> {
    val returner : HashMap<String, Any?> = hashMapOf()
    returner["email"] = emailId
    returner["metaMaskHash"] = "tempo"
    returner["name"] = name
    returner["password"] = passWord
    returner["phoneNumber"] = phoneNumber
    return returner
}
fun customToast(context: Context,toPrint : String){
    Toast.makeText(context, "$toPrint", Toast.LENGTH_LONG).show()
}
fun logInChecker(context: Context, dbConnect:FirebaseDatabase, userName: String, passWord: String, navController : NavController){
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        var usernameFetched = it.value
        if(usernameFetched != null){
            usernameFetched = usernameFetched as HashMap<String, String>
            usernameFetched.forEach{ mapUserChecker ->
                if(mapUserChecker.value == userName){
                    dbConnect.getReference("/userStore/${mapUserChecker.value}").get().addOnSuccessListener{
                            mapUserFinder ->
                        if(mapUserFinder.value != null) {
                            var passwordFetched:String = (mapUserFinder.value as HashMap<String, String>)["password"]!!
                            if(passwordFetched == passWord){
                                navController.navigate("Home")
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                            }
                            else{
                                // Feature
                                Toast.makeText(context, "Enter Correct Password", Toast.LENGTH_LONG).show()
                            }
                        }
                        else{
                            // Feature
                            Toast.makeText(context, "Problem Fetching Your Data. Contact Customer Care", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener{
                        Toast.makeText(context, "Unable to Fetch Data. Try Again", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(context, "No User Found, Please Sign In", Toast.LENGTH_LONG).show()
                    return@forEach
                }
            }
        }
        else{
            Toast.makeText(context, "No User Found, Please Sign In", Toast.LENGTH_LONG).show()
        }
    }.addOnFailureListener {
        // Feature
        Toast.makeText(context, "Check Your Internet Connection", Toast.LENGTH_LONG).show()
    }
}

fun signInChecker(context: Context, dbConnect:FirebaseDatabase, name: String, userName: String, emailId: String , phoneNumber: String, passWord: String, navController : NavController){
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        userListChecker ->
        if(userListChecker.value != null){
            var userListFetched =  userListChecker.value as HashMap<String, String>
             userListFetched.forEach{
                    aUser ->
                if(aUser.value == userName){
                    Toast.makeText(context, "Username is already used", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
                }
            }
        }
        var courier = userStoreDataGen(
            name = name,
            emailId = emailId,
            phoneNumber = phoneNumber,
            passWord = passWord
        )
        dbConnect.getReference("/userStore").child("$userName").setValue(courier).addOnSuccessListener {
            customToast(context = context, "Signed In Successfully")
            dbConnect.getReference("/usersList").push()/*.child("$indexCalculated")*/.setValue("$userName").addOnSuccessListener{
                Toast.makeText(context, "User Updated In Main List", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(context, "Unable To Update userList in Database", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener {
            Toast.makeText(context, "Unable To Create Account", Toast.LENGTH_LONG).show()
            navController.navigate("Login")

        }
    }.addOnFailureListener{
        Toast.makeText(context, "Unable to connect, Please Check Your Internet Connectivity", Toast.LENGTH_LONG).show()
    }
}

fun nftPoster(context: Context, dbConnect: FirebaseDatabase, bitmap: Uri?, name:String , price: String, description : String ){
    
}

















