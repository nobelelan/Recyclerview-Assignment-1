package com.example.recyclerview_assignment_1

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

/*Create a simple application where there will be a single activity. This activity contains a vertical
scrollable user list using recyclerview. Each user_list Item contains 4 children

(user profile img,user_name,user_phone_number,details_icon).

When pressed the details icon, it will display a custom alert dialog.
Inside this alert, the dialog will contain the user’s full address

(userProfileImg, userName, UserPhoneNumber, villageName, postOfficeName, districtName, divisionName, countryName)

and also will have a ‘close & cancel’ button in the alert dialog so that the user can close/cancel the alert dialog.*/

@Entity(tableName = "user_table")
data class User(
    val userName: String,
    val profileImage: Bitmap,
    val phoneNumber: String,
    val villageName: String,
    val postOfficeName: String,
    val districtName: String,
    val divisionName: String,
    val countryName: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}