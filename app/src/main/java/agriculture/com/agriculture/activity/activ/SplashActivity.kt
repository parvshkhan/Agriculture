package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Extra.BaseActivity
import agriculture.com.agriculture.activity.fragment.splashfragment.FragmentSplashOne
import agriculture.com.agriculture.activity.fragment.splashfragment.FragmentSplashThree
import agriculture.com.agriculture.activity.fragment.splashfragment.FragmentSplashTwo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_splash.*
import spencerstudios.com.bungeelib.Bungee
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper




class SplashActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            2 ->     btNext.text = resources.getText(R.string.getstarted)

            else -> btNext.text = resources.getText(R.string.next)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setAdapter();
        btNext.setOnClickListener()
        {
            when (viewPager.currentItem) {
                0 -> { viewPager.setCurrentItem(1)    }
                1 -> {viewPager.setCurrentItem(2)}
                2 -> {


                    if(Hawk.get<Boolean>("isFirst",true))
                    {
                        var intent : Intent ? = null
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        Bungee.zoom(this);  //fire the zoom animation
                        finish()
                    }
                    else
                    {
                        var intent : Intent ? = null
                        intent = Intent(this, DrawerActivity::class.java)
                        startActivity(intent)
                        Bungee.zoom(this);  //fire the zoom animation
                        finish()

                    }


                }

            }

        }



    }





    private fun setAdapter() {

        val pagerAdapter = MpagerAdapter(supportFragmentManager)
        viewPager?.adapter = pagerAdapter
        indicator.setViewPager(viewPager)
        viewPager.addOnPageChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    private class MpagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

        lateinit var fragment : Fragment

        override fun getItem(position: Int): Fragment {

            if(position == 0)
            {
                fragment = FragmentSplashOne()
            }
            else if(position == 1)
            {
                fragment = FragmentSplashTwo()
            }

            else if(position == 2)
            {
                fragment = FragmentSplashThree()
            }


            return  fragment

        }

        override fun getCount(): Int {
            return 3
        }

    }
}
