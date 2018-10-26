package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.fragment.IGetButtonTappedStatus
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_wishlist.view.*
import org.greenrobot.eventbus.EventBus






class WishlistAdapter(wishlist: PropertyList) : RecyclerView.Adapter<WishlistAdapter.MyViewHolder>() {

    var dataList : PropertyList? =wishlist
    var context : Context? = null


    override fun getItemCount(): Int {
        return dataList!!.payLoad.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_wishlist,parent,false))
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.itemView.textView33.text= dataList!!.payLoad.get(position).roi.toString() + "%"

        holder.itemView.textView32.text= dataList!!.payLoad.get(position).funded.toString() +"%"

        holder.itemView.textView34.text= dataList!!.payLoad.get(position).diffDate.toString()

        holder.itemView.textView31.text= "$" + dataList!!.payLoad.get(position).target.toString()

        holder.itemView.tvwishlistaddress.text= dataList!!.payLoad.get(position).address


        Picasso.get().load(dataList!!.payLoad.get(position).galleryImg).placeholder(R.drawable.ic_action_place_holder).into(holder.itemView.imagewishlistcustomerimage);


        if(dataList!!.payLoad.get(position).isSelect)
        {
            holder.itemView.imgfavouritewishlist.setImageResource(R.drawable.circletick)
            EventBus.getDefault().post("helkk")
        }
        else
        {
            holder.itemView.imgfavouritewishlist.setImageResource(R.drawable.circleuntick)
            EventBus.getDefault().post("helkk")
        }





        holder.itemView.imgfavouritewishlist.setOnClickListener {

            if(dataList!!.payLoad.get(position).isSelect)
            {
                holder.itemView.imgfavouritewishlist.setImageResource(R.drawable.circleuntick)
                dataList!!.payLoad.get(position).isSelect = false
                notifyDataSetChanged()
            }
            else
            {
                holder.itemView.imgfavouritewishlist.setImageResource(R.drawable.circletick)
                dataList!!.payLoad.get(position).isSelect = true
                notifyDataSetChanged()
            }


        }



    }



    internal inner class MyBounceInterpolator(amplitude: Double, frequency: Double) : android.view.animation.Interpolator {
        private var mAmplitude = 1.0
        private var mFrequency = 10.0

        init {
            mAmplitude = amplitude
            mFrequency = frequency
        }

        override fun getInterpolation(time: Float): Float {
            return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1).toFloat()
        }
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {




    }



}