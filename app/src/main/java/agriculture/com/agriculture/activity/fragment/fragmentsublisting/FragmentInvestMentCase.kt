package agriculture.com.agriculture.activity.fragment.fragmentsublisting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.PropertyListSub
import android.content.Context
import android.text.Html
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_invest_ment_case.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentInvestMentCase.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentInvestMentCase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInvestMentCase : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_invest_ment_case, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<PropertyListSub>("data") as PropertyListSub
        tvinvestdata.text = Html.fromHtml(data.payLoad.investmentCare)
        tvinvestdata.setMovementMethod(ScrollingMovementMethod())
    }

}
