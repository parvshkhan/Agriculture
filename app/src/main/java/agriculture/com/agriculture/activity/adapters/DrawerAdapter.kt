package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.row_drawer_items.view.*


class DrawerAdapter(listData : MutableList<String>) : RecyclerView.Adapter<DrawerAdapter.MyViewHolder>() {


    var myList : MutableList<String>? = null
    private var currentPosition = -1
    private var context: Context? = null

    var myArr  =  arrayOf(R.drawable.ic_action_personwhite, R.drawable.ic_action_profile, R.drawable.ic_action_money, R.drawable.ic_action_star, R.drawable.ic_action_resources,R.drawable.ic_action_heart,R.drawable.ic_action_settings,R.drawable.ic_action_logout)

    init {
        myList = listData
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_drawer_items,parent,false))
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.tvNameParent.text = myList!!.get(position)
        holder.itemView.clChild.setVisibility(View.GONE);
        holder.itemView.imageView2.background = null
        holder.itemView.imageView2.setImageResource(myArr[position])


        if (currentPosition == position) {
            val slideDown = AnimationUtils.loadAnimation(context, R.anim.dropdownanim)
            holder.itemView.clChild.setVisibility(View.VISIBLE)
            holder.itemView.clChild.startAnimation(slideDown)

            holder.itemView.imageView6.rotation = 270f


        }


            holder.itemView.ccParent.setOnClickListener(
                    {
                        if (position == 4) {
                            if (holder.itemView.clChild.visibility == View.VISIBLE)
                            {
                                holder.itemView.imageView6.rotation = 180f
                                holder.itemView.clChild.setVisibility(View.GONE)
                                return@setOnClickListener
                            }

                            currentPosition = position

                            notifyDataSetChanged()
                        }
                    }
            )

        if(position == 5)
            holder.itemView.borderchild.visibility = View.VISIBLE
        else
            holder.itemView.borderchild.visibility = View.GONE

    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){







    }

}