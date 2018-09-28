package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_blogs.*
import kotlinx.android.synthetic.main.row_blog.view.*
import kotlinx.android.synthetic.main.row_drawer_items.view.*
import kotlinx.android.synthetic.main.row_rc_main.view.*


class BlogAdapter(private var blogList: BlogResponse) : RecyclerView.Adapter<BlogAdapter.MyViewHolder>() {

    var dataList : BlogResponse? = blogList
    var context : Context ? = null





    override fun getItemCount(): Int {


        return dataList!!.payload.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_blog,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tvblogtitile.text=dataList!!.payload.get(position).postBy1
        holder.itemView.tvbloggername.text= dataList!!.payload.get(position).title
        holder.itemView.textView26.text= dataList!!.payload.get(position).description




    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {




    }

}