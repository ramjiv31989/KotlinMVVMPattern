package demo.test.mycolorview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.view.RxView
import demo.test.mycolorview.DataModel.AndroidData
import demo.test.mycolorview.DataModel.ItemClickListener
import demo.test.mycolorview.MainActivity
import demo.test.mycolorview.R
import kotlinx.android.synthetic.main.items.view.*

class EmpAdapter(
    var context: MainActivity,
    var mEmpList: ArrayList<AndroidData>,
    private val itemClick:ItemClickListener
) :
    RecyclerView.Adapter<EmpAdapter.EmpHolder>() {
    override fun getItemCount(): Int {
        return mEmpList.size
    }
    companion object {
        var mItemClickListener : ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return EmpHolder(view)
    }

    override fun onBindViewHolder(holder: EmpHolder, position: Int) {
        mItemClickListener = itemClick
        holder.tvFname?.text = mEmpList[position].name
        holder.tvLname?.text = mEmpList[position].apiLevel
        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(position,mEmpList[position].name)
        }

    }


    class EmpHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFname = view.tvFname
        val tvLname = view.tvLname
        val mView = view
    }
}