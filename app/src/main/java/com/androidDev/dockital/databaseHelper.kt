package com.androidDev.dockital

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay


fun userStoreDataGen(
    name: String,
    emailId: String ,
    phoneNumber: String,
    passWord: String
): HashMap<String, String> {
    return hashMapOf(
        "emailId" to emailId,
        "metaMaskHash" to "tempo",
        "name" to name,
        "passWord" to passWord,
        "phoneNumber" to phoneNumber
    )
}
fun nftsStoreDataGen(
    cloudPath: String,
    price: String,
    description: String,
    creator: String,
    owner: String,
    id: String
): HashMap<String, String>{
    return hashMapOf(
        "cloudPath" to cloudPath,
        "ownerPrice" to price,
        "ownerDescription" to description,
        "creator" to creator,
        "owner" to owner,
        "id" to id,
    )
}


fun customToast(context: Context,toPrint : String){
    Toast.makeText(context, "$toPrint", Toast.LENGTH_LONG).show()
}
fun logInChecker(
    context: Context,
    dbConnect:FirebaseDatabase,
    localStorageRef: SharedPreferences,
    userName: String,
    passWord: String,
    navController : NavController
){
    dbConnect.getReference("/usersList").get().addOnSuccessListener{
        var usernameFetched = it.value
        if(usernameFetched != null){
            usernameFetched = usernameFetched as HashMap<String, String>
            usernameFetched.forEach{ mapUserChecker ->
                if(mapUserChecker.value == userName){
                    dbConnect.getReference("/userStore/${mapUserChecker.value}").get().addOnSuccessListener{
                            mapUserFinder ->
                        if(mapUserFinder.value != null) {
                            var userDataFetched = mapUserFinder.value as HashMap<String, String>
                            var passwordFetched:String = userDataFetched["password"]!!
                            if(passwordFetched == passWord){
                                with(localStorageRef.edit()){
                                    this.putString(R.string.name.toString(),  userDataFetched["name"])
                                    this.putString(R.string.userName.toString(),  mapUserChecker.value)
                                    this.putString(R.string.passWord.toString(),  userDataFetched["password"])
                                    this.putString(R.string.metaMaskId.toString(),  userDataFetched["metaMaskHash"])
                                    this.putString(R.string.phoneNumber.toString(),  userDataFetched["phoneNumber"])
                                    this.putString(R.string.emailId.toString(),  userDataFetched["email"])
                                    apply()
                                }
                                navController.popBackStack()
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
                    return@addOnSuccessListener
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

fun signInChecker(
    context: Context,
    dbConnect:FirebaseDatabase,
    name: String,
    userName: String,
    emailId: String,
    phoneNumber: String,
    passWord: String,
    navController : NavController
){
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
            dbConnect.getReference("/usersList").push().setValue("$userName").addOnSuccessListener{
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

fun nftOwnerUpdater(){

}
fun nftMinter(
    context: Context,
    dbConnect: FirebaseDatabase,
    uri: Uri?,
    name:String,
    price: String,
    description : String,
    dbStorageConnect: FirebaseStorage
){
    dbStorageConnect.getReference("/images")
}















