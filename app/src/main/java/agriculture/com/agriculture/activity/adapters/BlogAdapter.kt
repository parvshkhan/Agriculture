package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_blog.view.*
import java.text.SimpleDateFormat
import java.util.*


class BlogAdapter(blogList: BlogResponse) : RecyclerView.Adapter<BlogAdapter.MyViewHolder>() {

    var dataList : BlogResponse? = blogList
    var context : Context ? = null

    override fun getItemCount(): Int {
        return dataList!!.payload.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_blog,parent,false))
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tvblogtitile.text=dataList!!.payload.get(position).title
        holder.itemView.tvbloggername.text= dataList!!.payload.get(position).postBy
        holder.itemView.textView26.text= Html.fromHtml(dataList!!.payload.get(position).description)

        val dataArr = dataList!!.payload.get(position).createdAt.split(" ")

        val df = SimpleDateFormat("yyyy-MM-dd")

        val res =   df.parse(dataArr[0])

        val c = Calendar.getInstance()
        c.setTime(res)

        val dayOfWeek = c.get(Calendar.DATE)
        val year = c.get(Calendar.YEAR)
        val month = getMonthShortName(c.get(Calendar.MONTH))

        holder.itemView.textView30.text = dayOfWeek.toString() +" "+ month.toString()
        holder.itemView.textView28.text = year.toString()


        Picasso.get().load(dataList!!.payload.get(position).image).placeholder(R.drawable.ic_action_place_holder).into(holder.itemView.imagewishlist);


        holder.itemView.textView27.setOnClickListener {
            if(holder.itemView.textView26.isExpanded)
            {
                holder.itemView.textView26.collapse()
                holder.itemView.textView27.text = "Read More >"
            }
            else
            {
                holder.itemView.textView26.expand()
                holder.itemView.textView27.text = "Read Less <"
            }



        }

    }




    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {




    }


    fun getMonthShortName(monthNumber: Int): String {
        var monthName = ""

        if (monthNumber >= 0 && monthNumber < 12)
            try {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.MONTH, monthNumber)

                val simpleDateFormat = SimpleDateFormat("MMM")
                //simpleDateFormat.setCalendar(calendar);
                monthName = simpleDateFormat.format(calendar.time)
            } catch (e: Exception) {
                e?.printStackTrace()
            }

        return monthName
    }

}