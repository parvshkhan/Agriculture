package agriculture.com.agriculture.activity.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.widget.ImageView


/**
 * A simple [Fragment] subclass.
 */
class FragmentInvestmentPlans : Fragment() {


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




    }

}// Required empty public constructor
