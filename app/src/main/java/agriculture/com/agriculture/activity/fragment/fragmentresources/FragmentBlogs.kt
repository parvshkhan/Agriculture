package agriculture.com.agriculture.activity.fragment.fragmentresources

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.BlogAdapter
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import agriculture.com.agriculture.activity.retrofit.RetrofitUtils
import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_fragment_blogs.*
import kotlinx.android.synthetic.main.fragment_fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentBlogs : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_blogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)

        rcBlog.layoutManager = LinearLayoutManager(activity)
        getBlogList();

        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }


    }
    fun getBlogList() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getBlogList().enqueue(object : Callback<BlogResponse> {
            override fun onFailure(call: Call<BlogResponse>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<BlogResponse>?, response: Response<BlogResponse>) {

                rcBlog.adapter = BlogAdapter(response!!.body() as BlogResponse)



            }
        })
    }


}// Required empty public constructor
