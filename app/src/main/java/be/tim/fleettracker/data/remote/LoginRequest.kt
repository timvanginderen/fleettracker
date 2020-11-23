package be.tim.fleettracker.data.remote

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("email")
    lateinit var email : String

    @SerializedName("password")
    lateinit var password : String
}