package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.PropertyAdapter
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.drawer_content_two.*
import kotlinx.android.synthetic.main.fragment_fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMain : Fragment() {



    private val ITEMS = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    private var adapter: ArrayAdapter<String>? = null
    private var lManager :  LinearLayoutManager ? = null

    val list = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)

        imgHamburger.setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }

        adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, ITEMS)
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter

        lManager = LinearLayoutManager(activity)
        rc.layoutManager = this.lManager

        getListData()


    }

    fun getListData() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getFarmList(Hawk.get("id")).enqueue(object : Callback<PropertyList> {
            override fun onFailure(call: Call<PropertyList>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<PropertyList>?, response: Response<PropertyList>?) {
                rc.adapter = PropertyAdapter(((response as Response<PropertyList>).body() as PropertyList))

            }
        })
    }


}
