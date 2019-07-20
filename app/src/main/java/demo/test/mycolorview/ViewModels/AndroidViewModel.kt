package demo.test.mycolorview.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import demo.test.mycolorview.CommonService.RetrofitService
import demo.test.mycolorview.DataModel.AndroidData

class  AndroidViewModel:ViewModel() {
    private val mService = RetrofitService()

    fun getAndroidData():MutableLiveData<List<AndroidData>>?{
        return mService.loadAndroidData()
    }
}