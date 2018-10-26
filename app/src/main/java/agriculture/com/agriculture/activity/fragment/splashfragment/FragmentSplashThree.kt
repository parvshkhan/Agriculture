package agriculture.com.agriculture.activity.fragment.splashfragment

import agriculture.com.agriculture.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * A simple [Fragment] subclass.
 */
class FragmentSplashThree : Fragment() {


    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_splash_three, container, false)
        return view
    }




}