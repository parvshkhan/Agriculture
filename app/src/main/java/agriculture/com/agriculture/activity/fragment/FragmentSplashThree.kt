package agriculture.com.agriculture.activity.fragment

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.MainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick

/**
 * A simple [Fragment] subclass.
 */
class FragmentSplashThree : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_splash_three, container, false)
        return view
    }




}