package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_knowledge_base.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentKnowledgeBase : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_knowledge_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)
        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }


        imgintroduction.setOnClickListener {

            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/introduction")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()

        }


        imghowitworks.setOnClickListener {
            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/introduction")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()


        }



        imgfarmtypes.setOnClickListener {
            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/farm_types")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()

        }



        imgaccnt.setOnClickListener {
            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/your_account")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()

        }


        imgregulationandpolicies.setOnClickListener {
            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/regulation_policies")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()

        }


        imgfaqandgallery.setOnClickListener {
            val freg=FragmentWebView()

            val bundle = Bundle()
            bundle.putString("url","http://smartit.ventures/farm/Agricultural_project/public/knowledge-base/faq_glossary")
            freg.arguments = bundle


            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,freg,"intowebviewfrag").addToBackStack(null).commit()


        }


    }

}// Required empty public constructor
