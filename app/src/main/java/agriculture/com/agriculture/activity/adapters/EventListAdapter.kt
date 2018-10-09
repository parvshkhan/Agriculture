package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.fragment.fragmentresources.FragmentSubListingEvent
import agriculture.com.agriculture.activity.modelresponse.EventResponse
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.row_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class EventListAdapter(evenlist: EventResponse) : RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {

    var dataList : EventResponse? = evenlist
    var context : Context? = null

    override fun getItemCount(): Int {
        return dataList!!.payload.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_event,parent,false))
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tveventtitle.text=dataList!!.payload.get(position).name
        holder.itemView.tveventtime.text= dataList!!.payload.get(position).startTime + dataList!!.payload.get(position).endTime

//        holder.itemView.textView26.text= dataList!!.payload.get(position).startDate + dataList!!.payload.get(position).endDate

        val dataArr1 = dataList!!.payload.get(position).startDate.split(" ")
        val dataArr2 = dataList!!.payload.get(position).endDate.split(" ")


        val timArr1 = dataList!!.payload.get(position).startTime.split(" ")
        val timArr2 = dataList!!.payload.get(position).endTime.split(" ")





        val df = SimpleDateFormat("yyyy-MM-dd")
        val tf = SimpleDateFormat("HH:mm:ss")
///////////////////////////////
        val res1 =   df.parse(dataArr1[0])
        val res2 =   df.parse(dataArr2[0])


        val restime1 =   tf.parse(timArr1[0])
        val restime2 =   tf.parse(timArr2[0])


        val cTime1 = Calendar.getInstance()
        cTime1.time = restime1


        val cTime2 = Calendar.getInstance()
        cTime2.time = restime2


        val delegate = "hh:mm a"

        holder.itemView.tveventtime.text= DateFormat.format(delegate, cTime1).toString() + "-"+  DateFormat.format(delegate, cTime2)



        val c1 = Calendar.getInstance()
        c1.setTime(res1)

        val dayOfWeek1 = c1.get(Calendar.DATE)
        val year1 = c1.get(Calendar.YEAR)
        val month1 = getMonthShortName(c1.get(Calendar.MONTH))
////////////////////////////


        val c2 = Calendar.getInstance()
        c2.setTime(res2)

        val dayOfWeek2 = c2.get(Calendar.DATE)
        val year2 = c2.get(Calendar.YEAR)
        val month2 = getMonthShortName(c2.get(Calendar.MONTH))


        holder.itemView.textView26.text = month1.toString() + " "+dayOfWeek1+", "+year1 +" - "+month2.toString() + " "+dayOfWeek2+", "+year2







        holder.itemView.btJoin.setOnClickListener {
            // context!!.startActivity(Intent(context,EventSubEventListingActivity::class.java).putExtra("id",dataList!!.payload.get(position).id))
            val freg= FragmentSubListingEvent()
            val bundle : Bundle
            bundle = Bundle()
            bundle.putInt("id",dataList!!.payload.get(position).id)
            freg.arguments = bundle
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"eventsublisting").addToBackStack(null).commit()
        }















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

        Picasso.get().load(dataList!!.payload.get(position).image).placeholder(R.drawable.ic_action_place_holder).transform(blurTransformation).into(holder.itemView.imgeventimage);

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