package com.androidDev.dockital

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.androidDev.dockital.navigations.NavigationItem
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.ktx.component1
import kotlinx.coroutines.delay
import okio.FileMetadata
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun userStoreDataGen(
    name: String,
    emailId: String ,
    phoneNumber: String,
    passWord: String,
    upiId: String
): HashMap<String, String> {
    return hashMapOf(
        "emailId" to emailId,
        "metaMaskHash" to "tempo",
        "name" to name,
        "upiId" to upiId,
        "passWord" to passWord,
        "phoneNumber" to phoneNumber
    )
}
fun nftsStoreDataGen(
    price: String,
    description: String,
    creator: String,
    owner: String
): HashMap<String, Any>{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return hashMapOf(
        "type" to "image",
        "owners" to hashMapOf<String, Any>(
            "$owner" to hashMapOf(
                "time" to (LocalDateTime.now().format(formatter)).toString(),
                "price" to "$price"
            )
        ),
        "creator" to "$creator",
        "currentPrice" to "$price",
        "description" to "$description"
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
                        println(mapUserFinder)
                        if(mapUserFinder.value != null) {
                            var userDataFetched = mapUserFinder.value as HashMap<String, Any?>
                            var passwordFetched:String = userDataFetched["passWord"].toString()
                            if(passwordFetched == passWord){
                                localStorageRef.edit().clear().commit()
                                with(localStorageRef.edit()){
                                    this.putString(R.string.name.toString(),  userDataFetched["name"].toString())
                                    this.putString(R.string.userName.toString(),  mapUserChecker.value)
                                    this.putString(R.string.passWord.toString(),  userDataFetched["passWord"].toString())
                                    this.putString(R.string.metaMaskId.toString(),  userDataFetched["metaMaskHash"].toString())
                                    this.putString(R.string.phoneNumber.toString(),  userDataFetched["phoneNumber"].toString())
                                    this.putString(R.string.emailId.toString(),  userDataFetched["email"].toString())
                                    this.putString(R.string.upiId.toString(),  userDataFetched["upiId"].toString())
                                    apply()
                                }
                                navController.popBackStack()
                                navController.navigate("Home")
                                navController.navigateUp()
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
            return@addOnSuccessListener
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
    upiId: String,
    navController : NavController,
    localStorageRef: SharedPreferences
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
            passWord = passWord,
            upiId = upiId
        )
        dbConnect.getReference("/userStore").child("$userName").setValue(courier).addOnSuccessListener {
            customToast(context = context, "Signed In Successfully")
            dbConnect.getReference("/usersList").push().setValue("$userName").addOnSuccessListener{
                navController.navigateUp()
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
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
    //Transferring OwnerShip
}
fun nftMinter(
    context: Context,
    uri: Uri?,
    name:String,
    price: String,
    description : String,
    dbConnect: FirebaseDatabase,
    dbStorageConnect: FirebaseStorage,
    localStorageRef: SharedPreferences
): Boolean{
    if(uri == null){
        customToast(context, "Please Select Image")
        return true
    }
    dbConnect.getReference("nftList").get().addOnSuccessListener {
        nftListFetchedFirebase ->
        var nftListFetched = nftListFetchedFirebase.value
        if(nftListFetched != null) {
            nftListFetched = (nftListFetchedFirebase.value as HashMap<String, String>).values
            nftListFetched.forEach { nftFetchedName ->
                if (name == nftFetchedName) {
                    customToast(context, "Name Already Used")
                    return@addOnSuccessListener
                }
            }
        }
        dbStorageConnect.reference.child("images/$name").putFile(uri).addOnSuccessListener {
            dbConnect.getReference("nftList").push().setValue("$name").addOnSuccessListener {
                val creatorOwner = localStorageRef.getString(R.string.userName.toString(), "NO_DEFINED_BY_DOCKITAL")!!
                val nftDataGen = nftsStoreDataGen(
                    price = price,
                    description = description,
                    creator = creatorOwner,
                    owner = creatorOwner
                )
                dbConnect.getReference("nftsStore").child("$name").setValue(nftDataGen).addOnSuccessListener {
                    customToast(context, "Mint Uploaded")
                }.addOnFailureListener{
                    customToast(context, "Mint Uploading Failed")
                    return@addOnFailureListener
                }
            }.addOnFailureListener{
                return@addOnFailureListener
            }
        }.addOnFailureListener{
            customToast(context = context, "Mint Failed")
            return@addOnFailureListener
        }
    }.addOnFailureListener{
        customToast(context, "Database Connection Error")
    }
    return true
}















