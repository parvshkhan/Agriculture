package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentDetail
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentDocuments
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentInvestMentCase
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentOverview
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_sub_listing.*
import kotlinx.android.synthetic.main.activity_sub_listing.view.*


class SubListingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_listing)
        imbacksublisting.setOnClickListener {
            finish()
        }


        vPager.adapter = MyAdapter(supportFragmentManager)

        tabLayout.setupWithViewPager(vPager,true)
        tabLayout.getTabAt(0)!!.setText(getResources().getText(R.string.overview));
        tabLayout.getTabAt(1)!!.setText(getResources().getText(R.string.documents));
        tabLayout.getTabAt(2)!!.setText(getResources().getText(R.string.investcase));
        tabLayout.getTabAt(3)!!.setText(getResources().getText(R.string.farmdetail));

        tabLayout.addOnTabSelectedListener(this)
        viewPager2.adapter = GalleryPagerAdapter(context = applicationContext)
        indicator2.setViewPager(viewPager2)

    }

    private class MyAdapter(fm: android.support.v4.app.FragmentManager) : FragmentStatePagerAdapter(fm)
    {

        override fun getItem(position: Int): Fragment {

            var fragment : Fragment ? = null

            when (position){
                0-> fragment = FragmentOverview()
                1-> fragment = FragmentDocuments()
                2-> fragment = FragmentInvestMentCase()
                3-> fragment = FragmentDetail()
            }
            return fragment!!
        }

        override fun getCount(): Int {

            return 4
        }


    }

    private class GalleryPagerAdapter(private val context: Context) : PagerAdapter() {

        internal var inflater: LayoutInflater? = null

        private val GalImages = intArrayOf(R.drawable.salman, R.drawable.salman, R.drawable.salman)

        override fun getCount(): Int {
            return GalImages.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val v0 = LayoutInflater.from(context).inflate(R.layout.row_gallery, null) as RelativeLayout

            val imageView = v0.findViewById<ImageView>(R.id.photo_thumb)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY)
            imageView.setImageResource(GalImages[position])
            container.addView(v0, 0)
            return v0
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as RelativeLayout)
        }
    }


}

private fun TabLayout.addOnTabSelectedListener(subListingActivity: SubListingActivity) {


    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF24976B"));
//    tabLayout.setSelectedTabIndicatorHeight(() (5 * getResources().getDisplayMetrics().density));
    tabLayout.setTabTextColors(Color.parseColor("#9b9b9b"), Color.parseColor("#FF24976B"));



}
