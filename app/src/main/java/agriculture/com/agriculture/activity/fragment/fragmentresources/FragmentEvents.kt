package agriculture.com.agriculture.activity.fragment.fragmentresources


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.EventListAdapter
import agriculture.com.agriculture.activity.modelresponse.EventResponse
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
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_events.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * A simple [Fragment] subclass.
 */
class FragmentEvents : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }

    private var lManager : Any?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)
        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }






        lManager = LinearLayoutManager(activity)

        rcEvent.layoutManager = lManager as LinearLayoutManager


        getEventResponse()



    }

    private fun getEventResponse() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getEventList().enqueue(object : Callback<EventResponse> {
            override fun onFailure(call: Call<EventResponse>?, t: Throwable?) {
                Toast.makeText(activity!!,t.toString(),Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<EventResponse>?, response: Response<EventResponse>?) {
                if(response!!.body()!!.isSuccess)
                {
if(rcEvent!=null)
                    rcEvent.adapter = EventListAdapter(response.body() as EventResponse)



                }
            }
        })



    }

}// Required empty public constructor
