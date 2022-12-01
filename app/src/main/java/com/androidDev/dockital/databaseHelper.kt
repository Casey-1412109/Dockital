package com.androidDev.dockital

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase


fun userStoreDataGen(name: String, emailId: String , phoneNumber: String, passWord: String): HashMap<String, Any?> {
    var returner : HashMap<String, Any?> = hashMapOf()
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
fun logInChecker(context: Context, dbConnect:FirebaseDatabase, emailId: String, passWord: String, navController : NavController){
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        var usernameFetched = it.value
        if(usernameFetched != null){
            usernameFetched = usernameFetched as ArrayList<String>
            run breaking@{
                usernameFetched.forEach{ mapUserChecker ->
                    if(mapUserChecker == emailId){
                        dbConnect.getReference("/userStore/${mapUserChecker}").get().addOnSuccessListener{
                                mapUserFinder ->
                            if(mapUserFinder.value != null) {
                                var passwordFetched:String = ( mapUserFinder.value as HashMap<String, String>)["password"]!!
                                if(passwordFetched == passWord){
                                    navController.navigate("Home")
                                    customToast(context, "Login Successful")
                                }
                                else{
                                    customToast(context, "Enter Correct Password") // Feature
                                }
                            }
                            else{
                                customToast(context, "Problem Fetching Your Data. Contact Customer Care") // Feature
                            }
                        }.addOnFailureListener{
                            customToast(context, "Unable to Fetch Data. Try Again")
                        }
                    }
                    else{
                        customToast(context, "No User Found, Please Sign In")
                        return@forEach
                    }
                }
            }
        }
        else{
            customToast(context, "No User Found, Please Sign In")
        }
    }.addOnFailureListener {
        customToast(context, "Check Your Internet Connection") // Feature
    }
}

fun signInChecker(context: Context, dbConnect:FirebaseDatabase, name: String, userName: String, emailId: String , phoneNumber: String, passWord: String, navController : NavController){
    var runnerConfig = false
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        userListChecker ->
        if(userListChecker.value != null){
//            run {
                var userListFetched =  userListChecker.value as HashMap<String, String>
                 userListFetched.forEach{
                        aUser ->
                    if(aUser.value == userName){
                        customToast(context, "Username is already used")
                        runnerConfig = true
                        return@addOnSuccessListener

                    }
                }

//            }
        }
        else {
            customToast(context, "Unable to check username availability")
        }
    }.addOnFailureListener{
        customToast(context, "Unable to connect, Please Check Your Internet Connectivity")
    }
    if(!runnerConfig){
        var courier = userStoreDataGen(
            name = name,
            emailId = emailId,
            phoneNumber = phoneNumber,
            passWord = passWord
        )
        customToast(context, "almsdklads")
        dbConnect.getReference("/userStore").child("$userName").setValue(courier).addOnSuccessListener {
            customToast(context = context, "Signed In Successfully")
//            dbConnect.getReference("/usersList").get().addOnSuccessListener{
//                userListFetched ->
////                var indexCalculated = ((userListFetched.value as HashMap<String, Any>).size - 1)
//                      #############
//            }.addOnFailureListener{
//                customToast(context = context, "Unable to Fetch usersList")
//            }
            dbConnect.getReference("/usersList").push()/*.child("$indexCalculated")*/.setValue("$userName").addOnSuccessListener{
                customToast(context = context, "User Updated In Main List")
            }.addOnFailureListener{
                customToast(context = context, "Unable To Update userList in Database")
            }

        }.addOnFailureListener {
            customToast(context, "Unable To Create Account")
            navController.navigate("Login")

        }
    }

}


fun nftPoster(context: Context, dbConnect: FirebaseDatabase, bitmap: Uri?, name:String , price: String, description : String ){

}

















