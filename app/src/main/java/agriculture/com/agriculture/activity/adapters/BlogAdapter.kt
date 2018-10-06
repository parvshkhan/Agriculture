package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
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


        holder.itemView.tvblogtitile.text=dataList!!.payload.get(position).postBy
        holder.itemView.tvbloggername.text= dataList!!.payload.get(position).title
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


//        imgProperty

        val blurTransformation = object : Transformation {
            override fun transform(source: Bitmap): Bitmap {
                val blurred = Blur.fastblur(context, source, 1)
                source.recycle()
                return blurred
            }

            override fun key(): String {
                return "blur()"
            }
        }

        Picasso.get().load(dataList!!.payload.get(position).image).placeholder(R.drawable.ic_action_place_holder).transform(blurTransformation).into(holder.itemView.imgblogimage);

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