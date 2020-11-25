package be.tim.fleettracker.data.remote

import com.google.gson.annotations.SerializedName

class LoginResponse(@field:SerializedName("token") var token: String?) {

}