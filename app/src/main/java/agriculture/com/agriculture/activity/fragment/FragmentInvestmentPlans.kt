package agriculture.com.agriculture.activity.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.InvestMentAdapter
import agriculture.com.agriculture.activity.adapters.WishlistAdapter
import agriculture.com.agriculture.activity.modelresponse.InvestmentDetailResponse
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_investment_plans.*
import kotlinx.android.synthetic.main.fragment_fragment_wish_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class FragmentInvestmentPlans : Fragment() {

    /*override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }*/
    var list : InvestmentDetailResponse? =null

    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_investment_plans, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }

        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

       api.investment().enqueue(object :Callback<InvestmentDetailResponse>{
           override fun onFailure(call: Call<InvestmentDetailResponse>?, t: Throwable?) {

           }

           override fun onResponse(call: Call<InvestmentDetailResponse>?, response: Response<InvestmentDetailResponse>?) {

               if(response!!.body()!!.isSuccess)
               {
                     if(response.body()?.payload?.isNotEmpty()!!) {
                       list = response!!.body() as InvestmentDetailResponse
                         if(rcInvestPlansParvesh!=null){
                       rcInvestPlansParvesh.visibility=View.VISIBLE

                       rcInvestPlansParvesh.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                       val adapter = InvestMentAdapter(list!!)
                       rcInvestPlansParvesh.adapter = adapter}
                   }
                   else
                   {

                       Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                   }
               }
               else
               {
                   Toast.makeText(activity, response.body()!!.message,Toast.LENGTH_SHORT).show()
               }

           }
       })





    }

    override fun onResume() {
        super.onResume()

        /*activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }*/
    }

}// Required empty public constructor



