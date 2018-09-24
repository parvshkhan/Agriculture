package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by android on 15/9/18.
 */
class PropertyAdapter : RecyclerView.Adapter<PropertyAdapter.MyViewHolder>{


    var data : MutableList<String>? = null

    constructor(dta : MutableList<String>)
    {
        data = dta

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rc_main,parent,false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return data?.count()!!
    }


    class  MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {




    }
}