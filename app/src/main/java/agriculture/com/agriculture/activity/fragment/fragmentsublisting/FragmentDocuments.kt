package agriculture.com.agriculture.activity.fragment.fragmentsublisting


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import agriculture.com.agriculture.R


/**
 * A simple [Fragment] subclass.
 */
class FragmentDocuments : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

}// Required empty public constructor
