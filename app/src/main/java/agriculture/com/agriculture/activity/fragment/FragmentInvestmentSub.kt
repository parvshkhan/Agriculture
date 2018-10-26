package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.InvestMentAdapter
import agriculture.com.agriculture.activity.modelresponse.InvestmentCommonIntroResponse
import agriculture.com.agriculture.activity.modelresponse.InvestmentDetailResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_fragment_web_view.*
import android.net.Proxy.getHost
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.fragment_fragment_investment_plans.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class FragmentInvestmentSub : Fragment() {


    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }
    var drawerLayout : DrawerLayout?  = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id =arguments!!.getString("id")


        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)
        drawerLayout = activity!!.findViewById<DrawerLayout>(R.id.drawer)

        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            drawerLayout!!.openDrawer(Gravity.START)
//            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            drawerLayout!!.closeDrawer(Gravity.START)
//            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }



        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)
        api.comonintro(id).enqueue(object : Callback<InvestmentCommonIntroResponse>{
            override fun onFailure(call: Call<InvestmentCommonIntroResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<InvestmentCommonIntroResponse>?, response: Response<InvestmentCommonIntroResponse>?) {
                if(response!!.body()!!.isSuccess)
                {
                    textView25.setText(response.body()!!.payload.title)
                    textView29.setText(Html.fromHtml(response.body()!!.payload.description))
                }
                else
                {
                    Toast.makeText(activity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        })





    }



}
