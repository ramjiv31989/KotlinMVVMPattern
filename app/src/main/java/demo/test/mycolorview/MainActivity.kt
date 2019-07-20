package demo.test.mycolorview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import demo.test.mycolorview.DataModel.AndroidData
import demo.test.mycolorview.DataModel.ItemClickListener
import demo.test.mycolorview.R.id.recyclerView
import demo.test.mycolorview.ViewModels.AndroidViewModel
import demo.test.mycolorview.adapter.EmpAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        getAndroidVersion()
    }

    private fun getAndroidVersion() {
        Log.e("getAndroidVersion", "yes")

        val mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(AndroidViewModel::class.java)
        mAndroidViewModel.getAndroidData()?.observe(this, Observer<List<AndroidData>> { androidList ->

            Log.e("list", androidList?.size.toString())
            recyclerView.adapter = EmpAdapter(this@MainActivity, androidList as ArrayList<AndroidData>, object :
                ItemClickListener {
                override fun onItemClick(pos: Int, name:String) {
                    Toast.makeText(applicationContext, "item "+pos+ "clicked"+ name, Toast.LENGTH_LONG).show()
                }
            })
        })

    }

}
