package demo.test.mycolorview.CommonService

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import demo.test.mycolorview.DataModel.AndroidData
import demo.test.mycolorview.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    val liveUserResponse:MutableLiveData<List<AndroidData>> = MutableLiveData()

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://learn2crack-json.herokuapp.com/api/")
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadAndroidData(): MutableLiveData<List<AndroidData>>? {

        Log.e("loadAndroidData","yes")

        val retrofitCall  = create().getAndroid()

        retrofitCall.enqueue(object : Callback<List<AndroidData>> {
            override fun onFailure(call: Call<List<AndroidData>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }

            override fun onResponse(call: Call<List<AndroidData>>, response: retrofit2.Response<List<AndroidData>>) {

                val list  = response.body()
                for (i in list.orEmpty()){
                    Log.e("on response 1:", i.name)

                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }

}