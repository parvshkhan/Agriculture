package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.WishlistAdapter
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_fragment_wish_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


interface IGetButtonTappedStatus{
    fun buttonTaped()

}
class FragmentWishList : Fragment() {



    var propertyList : PropertyList? = null


    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String) {
//        Toast.makeText(activity,"Hello",Toast.LENGTH_SHORT).show()

        var check = false
        for (item in propertyList!!.payLoad) {
            // body of loop
            if(item.isSelect){
                check = true
                break
            }
            else
                check = false

        }

        if(check)
        {

            btBuyNow.alpha = 1f
        }
        else
        {
            btBuyNow.alpha = 0f
        }


    };




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_wish_list, container, false)







    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callapi()

        btBuyNow.setOnClickListener {


                        Toast.makeText(activity,"Working on it..",Toast.LENGTH_SHORT).show()




        }
    }

    private fun callapi() {
        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getwishlist(Hawk.get<Int>("id").toString()).enqueue(object : Callback<PropertyList> {
            override fun onResponse(call: Call<PropertyList>?, response: Response<PropertyList>?) {

                if(response!!.body()!!.isSuccess)
                {

                    if(response.body()?.payLoad?.isNotEmpty()!!) {
                        try {
                            btBuyNow.visibility = View.VISIBLE
                            propertyList = response!!.body() as PropertyList
                            imageView16.visibility=View.GONE
                            textView16.visibility=View.GONE
                            rcInvestPlans.visibility=View.VISIBLE
                            rcInvestPlans.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                            val adapter = WishlistAdapter(propertyList!!)
                            rcInvestPlans.adapter = adapter
                        } catch (e: Exception) {
                        }
                    }
                    else
                    {

                        Toast.makeText(activity, response.body()!!.message,Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    try {
                        imageView16.visibility=View.VISIBLE
                        textView16.visibility=View.VISIBLE
                        rcInvestPlans.visibility=View.GONE
                        btBuyNow.visibility = View.INVISIBLE
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onFailure(call: Call<PropertyList>?, t: Throwable?) {

                Toast.makeText(activity,t.toString(),Toast.LENGTH_SHORT).show()
            }
        })




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }

        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)
    }


}// Required empty public constructor
