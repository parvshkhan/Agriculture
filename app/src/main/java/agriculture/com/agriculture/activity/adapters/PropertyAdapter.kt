package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.activ.SubListingActivity
import agriculture.com.agriculture.activity.modelresponse.AddRemoveWishlist
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.modelresponse.RemoveWishListResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.row_rc_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import spencerstudios.com.bungeelib.Bungee
import agriculture.com.agriculture.activity.adapters.PropertyAdapter.MyBounceInterpolator






class PropertyAdapter(dta: PropertyList) : RecyclerView.Adapter<PropertyAdapter.MyViewHolder>() {


    var dataList : PropertyList? = dta
    var context : Context ? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rc_main,parent,false)
        context = parent.context
        return MyViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tvdelaername.text = "By: "+dataList!!.payLoad.get(position).owner
        holder.itemView.tvamout.text= "$"+dataList!!.payLoad.get(position).target.toString()
        holder.itemView.tvfargmaddress.text= dataList!!.payLoad.get(position).address
        holder.itemView.tvinvester.text= dataList!!.payLoad.get(position).funded.toString() +"%"
        holder.itemView.tvroi.text= dataList!!.payLoad.get(position).roi.toString()+ "%"
        holder.itemView.tvdayleft.text= dataList!!.payLoad.get(position).diffDate.toString()
        holder.itemView.seekProgress.max = dataList!!.payLoad.get(position).totalFund.toInt()
        holder.itemView.seekProgress.progress = dataList!!.payLoad.get(position).funded.toInt()



        if (dataList!!.payLoad.get(position).wishlist==0)
        {
            holder.itemView.imageView5.setImageResource(R.drawable.heart_off)
        }
        else
        {
            holder.itemView.imageView5.setImageResource(R.drawable.heart_on)
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

        holder.itemView.imageView5.setOnClickListener {

            if(dataList!!.payLoad.get(position).wishlist != 0)
            {
                holder.itemView.imageView5.isClickable = false
                removeFromWishList(position,holder)

                val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                val interpolator = MyBounceInterpolator(0.2, 20.0)
                myAnim.interpolator = interpolator
                holder.itemView.imageView5.startAnimation(myAnim);
                holder.itemView.imageView5.setImageResource(R.drawable.heart_off)

            }
            else
            {
                holder.itemView.imageView5.isClickable = false
                addToWishList(position,holder)

                val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);

                val interpolator = MyBounceInterpolator(0.2, 20.0)
                myAnim.interpolator = interpolator




                holder.itemView.imageView5.startAnimation(myAnim);





                holder.itemView.imageView5.setImageResource(R.drawable.heart_on)
            }


        }



holder.itemView.imageView3.background=null
        Picasso.get().load(dataList!!.payLoad.get(position).galleryImg).placeholder(R.drawable.ic_action_place_holder).into(holder.itemView.imagewishlist);
        Picasso.get().load(dataList!!.payLoad.get(position).ownerImg).placeholder(R.drawable.ic_action_place_holder).into(holder.itemView.imageView3);


        holder.itemView.btopen.setOnClickListener {

            context!!.startActivity(Intent(context, SubListingActivity::class.java).putExtra("id",dataList!!.payLoad.get(position).id))
            Bungee.zoom(context)
        }





    }

    private fun addToWishList(position: Int, holder: MyViewHolder) {


        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.addWishList(Hawk.get<Int>("id").toString(), dataList?.payLoad?.get(position)?.id.toString()).enqueue(object : Callback<AddRemoveWishlist> {
            override fun onFailure(call: Call<AddRemoveWishlist>?, t: Throwable?) {
                holder.itemView.imageView5.isClickable = true
                Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<AddRemoveWishlist>?, response: Response<AddRemoveWishlist>?) {
                holder.itemView.imageView5.isClickable = true
                if(response?.body()?.isSuccess!!)
                {

                    dataList!!.payLoad.get(position).wishlist = response.body()!!.payLoad.id
                    notifyDataSetChanged()
                }
                else
                {
                    holder.itemView.imageView5.setImageResource(R.drawable.heart_off)
                    Toast.makeText(context, response.body()!!.message,Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

    private fun removeFromWishList(position: Int, holder: MyViewHolder) {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.removeWishList(dataList?.payLoad?.get(position)?.wishlist.toString()).enqueue(object : Callback<RemoveWishListResponse> {
            override fun onFailure(call: Call<RemoveWishListResponse>?, t: Throwable?) {
                holder.itemView.imageView5.isClickable = true

                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<RemoveWishListResponse>?, response: Response<RemoveWishListResponse>?) {
                holder.itemView.imageView5.isClickable = true

                if(response?.body()?.isSuccess!!)
                {
                    dataList!!.payLoad.get(position).wishlist = 0
                    notifyDataSetChanged()
                }
                else
                {
                    holder.itemView.imageView5.setImageResource(R.drawable.heart_on)
                    Toast.makeText(context, response.body()!!.message,Toast.LENGTH_SHORT).show()
                }
            }
        })




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
}