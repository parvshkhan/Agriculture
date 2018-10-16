package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_fragment_web_view.*
import android.net.Proxy.getHost
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient




class FragmentWebView : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments!!.getString("url")


        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)
        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }

        webview.loadUrl(arguments!!.getString("url"))
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webview.webViewClient = MyWebViewClient()



    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return true
        }
    }

}
