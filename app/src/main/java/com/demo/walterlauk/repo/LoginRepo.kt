package com.demo.walterlauk.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.demo.walterlauk.model.Login
import com.demo.walterlauk.model.Parts
import com.demo.walterlauk.network.ApiInterface
import com.demo.walterlauk.network.Responce
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginRepo(val mApi : ApiInterface , val mContext: Context) {

    val mUserLogin = MutableLiveData<Responce<Login>>()

    suspend fun getUserLogin(user_name : String , password : String){
        val dataJson = JSONObject()
        dataJson.put("user_name", user_name)
        dataJson.put("password" , password)
        val requestBody = dataJson.toString().toRequestBody("application/json".toMediaTypeOrNull())


        val result = mApi.getUserLogin(requestBody)
        if (result.body() != null) {
            mUserLogin.postValue(Responce.Success(result.body()!!))
        }
    }



    val mParts = MutableLiveData<Responce<Parts>>()

    suspend fun getParts(token : String){
        val dataJson = JSONObject()
        dataJson.put("token", token)
        val requestBody = dataJson.toString().toRequestBody("application/json".toMediaTypeOrNull())


        val result = mApi.getTruckTrailerParts(requestBody)
        if (result.body() != null) {
            mParts.postValue(Responce.Success(result.body()!!))
        }
    }

}