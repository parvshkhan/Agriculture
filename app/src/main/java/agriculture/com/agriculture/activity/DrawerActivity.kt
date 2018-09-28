package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.DrawerAdapter
import agriculture.com.agriculture.activity.adapters.PropertyAdapter
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.retrofit.RetrofitUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.drawer_content.*
import android.widget.ArrayAdapter
import retrofit2.Response
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import kotlinx.android.synthetic.main.drawer_content_two.*


class DrawerActivity : AppCompatActivity(),ICallback{




    private val ITEMS = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    private var adapter: ArrayAdapter<String>? = null
    private var lManager :  LinearLayoutManager ? = null

    val list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        imgHamburger.setOnClickListener {
            if(!drawer.isDrawerOpen(Gravity.START))
                drawer.openDrawer(Gravity.START)
        }

        imgCloseDrawer2.setOnClickListener {
            if(drawer.isDrawerOpen(Gravity.START))
                drawer.closeDrawer(Gravity.START)
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ITEMS)
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter

        lManager = LinearLayoutManager(this)
        rc.layoutManager = this.lManager


        list.add(applicationContext.resources.getString(R.string.myaccount))
        list.add(applicationContext.resources.getString(R.string.investmentplan))
        list.add(applicationContext.resources.getString(R.string.farm))
        list.add(applicationContext.resources.getString(R.string.premiumservice))
        list.add(applicationContext.resources.getString(R.string.resources))
        list.add(applicationContext.resources.getString(R.string.wishlist))
        list.add(applicationContext.resources.getString(R.string.settings))
        list.add(applicationContext.resources.getString(R.string.logout))


        rcDrawer.layoutManager = LinearLayoutManager(this)
        rcDrawer.adapter = DrawerAdapter(list!!)



        /*llprofile.visibility = View.GONE



        llmyaccnt.setOnClickListener({ l ->

            //creating an animation
            val slideDown = AnimationUtils.loadAnimation(this, R.anim.dropdownanim)
            //toggling visibility
            llprofile.setVisibility(View.VISIBLE);
            //adding sliding effect
            llprofile.startAnimation(slideDown);

        })*/


    }

    override fun apiresponse(apiresponse: Any) {


        if(apiresponse is Throwable)   // if error occures return
            return
//        ((apiresponse as Response<PropertyList>).body() as PropertyList)
        rc.adapter = PropertyAdapter(((apiresponse as Response<PropertyList>).body() as PropertyList))

    }

    override fun onResume() {
        super.onResume()

        RetrofitUtils(this).getListData()
    }



}
