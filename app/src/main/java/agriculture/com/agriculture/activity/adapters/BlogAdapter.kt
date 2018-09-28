package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_blogs.*


class BlogAdapter(private var blogList: BlogResponse) : RecyclerView.Adapter<BlogAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        return 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_blog,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {




    }

}