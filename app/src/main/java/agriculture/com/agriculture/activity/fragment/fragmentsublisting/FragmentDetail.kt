package agriculture.com.agriculture.activity.fragment.fragmentsublisting


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.activ.PropertyListSub
import android.text.Html
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentDetail : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<PropertyListSub>("data") as PropertyListSub
        tvfarmdata.text = Html.fromHtml(data.payLoad.farmDetail)


    }

}// Required empty public constructor
