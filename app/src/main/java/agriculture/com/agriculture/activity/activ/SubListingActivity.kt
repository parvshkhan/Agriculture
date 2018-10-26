package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentDetail
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentDocuments
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentInvestMentCase
import agriculture.com.agriculture.activity.fragment.fragmentsublisting.FragmentOverview
import agriculture.com.agriculture.activity.modelresponse.PropertyListSub
import agriculture.com.agriculture.activity.retrofit.RetrofitUtils
import android.content.Context
import android.graphics.Bitmap
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
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.activity_sub_listing.*
import kotlinx.android.synthetic.main.activity_sub_listing.view.*
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import de.cketti.shareintentbuilder.ShareIntentBuilder
import android.content.Intent




class SubListingActivity : AppCompatActivity(),ICallback {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_listing)

        if(intent.hasExtra("id"))
        {
            val id  = intent.getIntExtra("id",2)
            val rtData = RetrofitUtils(this)
            rtData.getSubListData(id)
        }

        imbacksublisting.setOnClickListener {
            finish()
        }

        imgshare.setOnClickListener {
            val shareIntent = ShareIntentBuilder.from(applicationContext)
                    .text("Text to share")
                    .build()

            startActivity(shareIntent)
        }

    }

    private class MyAdapter(fm: android.support.v4.app.FragmentManager,data : PropertyListSub) : FragmentStatePagerAdapter(fm)
    {
        val  subListData  = data


        override fun getItem(position: Int): Fragment {

            var fragment : Fragment ? = null

            when (position){
                0-> {
                    val bundle  = Bundle()
                    bundle.putParcelable("data",subListData)
                    fragment = FragmentOverview()
                    fragment.arguments = bundle
                    fragment.arguments = bundle
                }
                1->{
                    val bundle  = Bundle()
                    bundle.putParcelable("data",subListData)
                    fragment = FragmentDocuments()
                    fragment.arguments = bundle
                }
                2-> {
                    val bundle  = Bundle()
                    bundle.putParcelable("data",subListData)
                    fragment = FragmentInvestMentCase()
                    fragment.arguments = bundle
                }


                3-> {
                    val bundle  = Bundle()
                    bundle.putParcelable("data",subListData)
                    fragment = FragmentDetail()
                    fragment.arguments = bundle
                }
            }
            return fragment!!
        }

        override fun getCount(): Int {

            return 4
        }


    }

    private class GalleryPagerAdapter(private val context: Context,val imgArr: PropertyListSub) : PagerAdapter() {


        val blurTransformation = object : Transformation {
            override fun transform(source: Bitmap): Bitmap {
                val blurred = Blur.fastblur(context, source, 1)
                source.recycle()
                return blurred
            }

            override fun key(): String {
                return "blur()"
            }
        }
        override fun getCount(): Int {
            return imgArr.payLoad.galleryImg.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val v0 = LayoutInflater.from(context).inflate(R.layout.row_gallery, null) as RelativeLayout

            val imageView = v0.findViewById<ImageView>(R.id.photo_thumb)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY)
            Picasso.get().load(imgArr.payLoad.galleryImg.get(position)).into(imageView);
            container.addView(v0, 0)
            return v0
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as RelativeLayout)
        }
    }

    override fun apiresponse(apiresponse: Any) {

        if(apiresponse is Throwable) {
            Toast.makeText(applicationContext,(apiresponse as Throwable).toString(),Toast.LENGTH_SHORT).show()
            return
        }

        val dataSubListing = ((apiresponse as Response<PropertyListSub>).body() as PropertyListSub)

        if(dataSubListing.payLoad.wishlist == 0)
        {
            imageView9.background = null
            imageView9.setImageResource(R.drawable.heart_off)

        }
        else
        {
            imageView9.background = null
            imageView9.setImageResource(R.drawable.heart_off)

        }

//


        vPager.adapter = MyAdapter(supportFragmentManager, dataSubListing)
        tabLayout.setupWithViewPager(vPager,true)
        tabLayout.getTabAt(0)!!.setText(getResources().getText(R.string.overview));
        tabLayout.getTabAt(1)!!.setText(getResources().getText(R.string.documents));
        tabLayout.getTabAt(2)!!.setText(getResources().getText(R.string.investcase));
        tabLayout.getTabAt(3)!!.setText(getResources().getText(R.string.farmdetail));

        tabLayout.addOnTabSelectedListener(this)





        setGalleryImage(dataSubListing)

        tvownername.text = dataSubListing!!.payLoad.owner
        tvfarmaddress.text = dataSubListing!!.payLoad.address
        imageView10.background = null
        Picasso.get().load(dataSubListing!!.payLoad.ownerImg).placeholder(R.drawable.ic_action_place_holder).into(imageView10);


    }

    private fun setGalleryImage(dataSubListing: PropertyListSub) {


        viewPager2.adapter = GalleryPagerAdapter(context = applicationContext, imgArr = dataSubListing)
        indicator2.setViewPager(viewPager2)
    }
}

private fun TabLayout.addOnTabSelectedListener(subListingActivity: SubListingActivity) {


    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF24976B"));
//    tabLayout.setSelectedTabIndicatorHeight(() (5 * getResources().getDisplayMetrics().density));
    tabLayout.setTabTextColors(Color.parseColor("#9b9b9b"), Color.parseColor("#FF24976B"));



}
