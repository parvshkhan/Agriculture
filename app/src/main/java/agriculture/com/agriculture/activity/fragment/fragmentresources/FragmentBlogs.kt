package agriculture.com.agriculture.activity.fragment.fragmentresources

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.BlogAdapter
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import agriculture.com.agriculture.activity.retrofit.RetrofitUtils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_blogs.*
import retrofit2.Response


class FragmentBlogs : Fragment(),ICallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_blogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcBlog.layoutManager = LinearLayoutManager(activity)
        RetrofitUtils(activity!!).getBlogList()
    }



    override fun apiresponse(apiresponse: Any) {
        rcBlog.adapter = BlogAdapter(((apiresponse as Response<BlogResponse>).body() as BlogResponse))
//        DateFormat.getDateInstance(DateFormat.SHORT).format(Date(""))

    }

}// Required empty public constructor
