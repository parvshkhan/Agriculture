package agriculture.com.agriculture.activity.fragment.fragmentsublisting

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentInvestMentCase.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentInvestMentCase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInvestMentCase : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_invest_ment_case, container, false)
    }

}
