package com.demo.walterlauk.network

import com.demo.walterlauk.model.Login
import com.demo.walterlauk.model.Parts
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiInterface {

    //Api for user Login
    @POST("v1/auth/login")
    suspend fun getUserLogin(@Body requestBody: RequestBody) : Response<Login>


    //Api for get Parts
    @POST("v1/get/trucktrailer/parts")
    suspend fun getTruckTrailerParts(@Body requestBody: RequestBody) : Response<Parts>


    companion object {
        private val baseUrl : String = "https://wlcsapp.de/walter_lauk/api/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder().client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        private fun client(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .addInterceptor(interceptor)
                .build()
        }
    }


}