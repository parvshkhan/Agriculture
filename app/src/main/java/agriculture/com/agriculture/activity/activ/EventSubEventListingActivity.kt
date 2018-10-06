package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.modelresponse.EventSubLIstingResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.format.DateFormat
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.activity_event_sub_event_listing.*
import kotlinx.android.synthetic.main.row_event.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EventSubEventListingActivity : AppCompatActivity() {

    var id : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_sub_event_listing)








        if(intent!=null)
        {
            if(intent.hasExtra("id"))
            {
                id = intent.getIntExtra("id",0)
                getSubListEventdata(id!!)
            }
        }
    }

    private fun getSubListEventdata(id: Int) {


        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getSubEventList(id.toString()).enqueue(object : Callback<EventSubLIstingResponse> {
            override fun onFailure(call: Call<EventSubLIstingResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext!!,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<EventSubLIstingResponse>?, response: Response<EventSubLIstingResponse>?) {

                if(response!!.body()!!.isSuccess)
                {
                    tveventtitlesub.text = response.body()!!.payload.name
                    tvaddresslisting.text = response.body()!!.payload.location
                    val dataArr1 = response!!.body()!!.payload.startDate.split(" ")
                    val dataArr2 = response!!.body()!!.payload.endDate.split(" ")


                    val timArr1 = response!!.body()!!.payload.startTime.split(" ")
                    val timArr2 = response!!.body()!!.payload.endTime.split(" ")


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




                    tveventtimesub.text= DateFormat.format(delegate, cTime1).toString() + "-"+  DateFormat.format(delegate, cTime2)








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






                    tveventdate.text =  month1.toString() + " "+dayOfWeek1+", "+year1 +" - "+month2.toString() + " "+dayOfWeek2+", "+year2

                    tvdescEvent.text = Html.fromHtml(response.body()!!.payload.description)



                    val blurTransformation = object : Transformation {
                        override fun transform(source: Bitmap): Bitmap {
                            val blurred = Blur.fastblur(applicationContext, source, 1)
                            source.recycle()
                            return blurred
                        }

                        override fun key(): String {
                            return "blur()"
                        }
                    }

                    Picasso.get().load(response!!.body()!!.payload.image).placeholder(R.drawable.ic_action_place_holder).transform(blurTransformation).into(imgeventimagesub);







                }



            }
        })



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
