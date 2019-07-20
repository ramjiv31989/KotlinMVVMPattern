package demo.test.mycolorview.interfaces

import demo.test.mycolorview.DataModel.AndroidData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("android")
    fun getAndroid(): Call<List<AndroidData>>
}