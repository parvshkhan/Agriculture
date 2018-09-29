package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.DrawerAdapter
import agriculture.com.agriculture.activity.fragment.FragmentMain
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.drawer_content_two.*


class DrawerActivity : AppCompatActivity(){






    val list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)



        list.add(applicationContext.resources.getString(R.string.myaccount))
        list.add(applicationContext.resources.getString(R.string.investmentplan))
        list.add(applicationContext.resources.getString(R.string.farm))
        list.add(applicationContext.resources.getString(R.string.premiumservice))
        list.add(applicationContext.resources.getString(R.string.resources))
        list.add(applicationContext.resources.getString(R.string.wishlist))
        list.add(applicationContext.resources.getString(R.string.settings))
        list.add(applicationContext.resources.getString(R.string.logout))


        rcDrawer.layoutManager = LinearLayoutManager(this)
        rcDrawer.adapter = DrawerAdapter(list)


        val frag : FragmentMain? = FragmentMain()

        supportFragmentManager.beginTransaction().add(R.id.drawercontainer,frag,"mainfrag").commit()

        setdefaultValue()





    }

    private fun setdefaultValue() {


        val name = Hawk.get<String>("name",null)
        val email = Hawk.get<String>("email",null)

        if(name!=null && email !=null){
            textView5.text = name
            textView6.text = email}




    }


}
