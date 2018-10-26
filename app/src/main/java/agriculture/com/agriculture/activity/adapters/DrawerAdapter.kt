package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.R.id.drawer
import agriculture.com.agriculture.activity.fragment.*
import agriculture.com.agriculture.activity.fragment.fragmentresources.FragmentBlogs
import agriculture.com.agriculture.activity.fragment.fragmentresources.FragmentEvents
import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.*
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


        holder.itemView.tvbloglistopen.setOnClickListener({p ->

        val freg=FragmentBlogs()
            (context as AppCompatActivity).supportFragmentManager.popBackStack();
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"blogfrah").commit()

        })


       holder.itemView.tvEventOpen.setOnClickListener({p ->

        val freg=FragmentEvents()
           (context as AppCompatActivity).supportFragmentManager.popBackStack();
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"eventfrag").commit()

        })

        holder.itemView.tvknowledgebase.setOnClickListener({p->
            val freg=FragmentKnowledgeBase()
            (context as AppCompatActivity).supportFragmentManager.popBackStack();
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"knowledgefrag").commit()

        }
     )





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



                        if(position == 2)
                        {
                            (context as AppCompatActivity).supportFragmentManager.popBackStack();
                            val frag : FragmentMain? = FragmentMain()
                            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,frag,"mainfrag").commit()
                        }


                        if(position == 0 )
                        { (context as AppCompatActivity).supportFragmentManager.popBackStack();
                            val frag : FragmentMyAccount? = FragmentMyAccount()
                            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,frag,"accntfrag").commit()

                        }

                        if(position == 1)
                        { (context as AppCompatActivity).supportFragmentManager.popBackStack();
                            val frag : FragmentInvestmentPlans? = FragmentInvestmentPlans()
                            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,frag,"investmentplanfrag").commit()


                        }

                        if(position == 5)
                        { (context as AppCompatActivity).supportFragmentManager.popBackStack();
                            val frag : FragmentWishList? = FragmentWishList()
                            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,frag,"wishlistfrag").commit()
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