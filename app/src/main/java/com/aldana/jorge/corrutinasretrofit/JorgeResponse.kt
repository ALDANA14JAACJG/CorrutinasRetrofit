package com.aldana.jorge.corrutinasretrofit

import com.google.gson.annotations.SerializedName

data class JorgeResponse(
    @SerializedName("status") var status:String,
    @SerializedName("message") var images:List<String>
    )



