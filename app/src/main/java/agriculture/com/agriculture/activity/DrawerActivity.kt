package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.PropertyAdapter
import agriculture.com.agriculture.activity.adapters.PropertyList
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.model.Users
import agriculture.com.agriculture.activity.retrofit.RetrofitUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.drawer_content.*
import android.widget.ArrayAdapter
import java.util.*


class DrawerActivity : AppCompatActivity(),ICallback{
    override fun apiresponse(apiresponse: Any) {
        print("result"+apiresponse);

//        listOf(apiresponse as PropertyList.PayLoad).get(0).
    }


    private val ITEMS = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")
    private var adapter: ArrayAdapter<String>? = null
    private var lManager :  LinearLayoutManager ? = null

    val mutableList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        imgHamburger.setOnClickListener {
            if(!drawer.isDrawerOpen(Gravity.START))
                drawer.openDrawer(Gravity.START)
        }

        imgCloseDrawer.setOnClickListener {
            if(drawer.isDrawerOpen(Gravity.START))
                drawer.closeDrawer(Gravity.START)
        }


        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ITEMS)
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter


        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")
        mutableList.add("Madhu")


        lManager = LinearLayoutManager(this)
        rc.layoutManager = lManager
        rc.adapter = PropertyAdapter(mutableList)



        RetrofitUtils(this).getListData()








    }





}
