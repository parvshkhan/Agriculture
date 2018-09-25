package agriculture.com.agriculture.activity.fragment.fragmentsublisting


import agriculture.com.agriculture.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class Fragment_Overview : Fragment() {

    var  viewFrag : View ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewFrag = inflater.inflate(R.layout.fragment__over_view, container, false)
        return viewFrag
    }

}
