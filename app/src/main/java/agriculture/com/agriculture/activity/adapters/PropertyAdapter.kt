package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.SubListingActivity
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.row_rc_main.view.*


class PropertyAdapter(dta: PropertyList) : RecyclerView.Adapter<PropertyAdapter.MyViewHolder>() {


    var dataList : PropertyList? = dta
    var context : Context ? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rc_main,parent,false)
        context = parent.context
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tvdelaername.text = dataList!!.payLoad.get(position).name
        holder.itemView.tvamout.text= "$"+dataList!!.payLoad.get(position).totalFund.toString()

//        holder.itemView.btopen.text= dataList!!.payLoad.get(position).name
        holder.itemView.tvfargmaddress.text= dataList!!.payLoad.get(position).address
        holder.itemView.tvinvester.text= dataList!!.payLoad.get(position).owner
        holder.itemView.tvroi.text= dataList!!.payLoad.get(position).roi.toString()
        holder.itemView.tvdayleft.text= dataList!!.payLoad.get(position).diffDate.toString()


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

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").transform(blurTransformation).into(holder.itemView.imgProperty);
//        Picasso.get().load(dataList!!.payLoad.get(position).galleryImg).placeholder(R.drawable.ic_action_place_holder).transform(blurTransformation).into(holder.itemView.imgProperty);


holder.itemView.btopen.setOnClickListener {

    context!!.startActivity(Intent(context,SubListingActivity::class.java))

}





    }

    override fun getItemCount(): Int {
        return dataList!!.payLoad.size
    }


    @Suppress("CAST_NEVER_SUCCEEDS")
    class  MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        /*  private  var tvDelarName : TextView
          private  var tvAmount : TextView
          private  var tvAddress : TextView
          private  var btOpen : TextView
          private  var tvInvester : TextView
          private  var tvRoi : TextView
          private  var tvDaysLeft : TextView
          private  var tvSeekBarProgress: SeekBar
          private lateinit var imgProperty: ImageView


          init {
              tvDelarName = itemView!!.tvdelaername as TextView
              tvAmount = itemView.tvamout as TextView
              tvAddress = itemView.tvfargmaddress as TextView
              btOpen = itemView.btopen as Button
              tvInvester = itemView.seekProgress as TextView
              tvRoi = itemView.seekProgress as TextView
              tvDaysLeft = itemView.seekProgress as TextView
              imgProperty = itemView.imgProperty as ImageView
              tvSeekBarProgress = itemView.seekProgress as SeekBar


          }*/




    }
}