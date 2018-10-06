package agriculture.com.agriculture.activity.fragment.splashfragment

import agriculture.com.agriculture.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FragmentSplashTwo : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_splash_two, container, false)

        return view
    }

}