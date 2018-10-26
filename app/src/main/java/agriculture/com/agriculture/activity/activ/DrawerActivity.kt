package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.adapters.DrawerAdapter
import agriculture.com.agriculture.activity.fragment.FragmentMain
import agriculture.com.agriculture.activity.modelresponse.ResponseShowProfile
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drawer_content_two.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class DrawerActivity : AppCompatActivity(){



    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


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

        supportFragmentManager.beginTransaction().add(R.id.drawercontainer,frag,"mainfrag").addToBackStack(null).commit()

        setdefaultValue()


    }

    private fun setdefaultValue() {


        val name = Hawk.get<String>("name",null)
        val email = Hawk.get<String>("email",null)

        if(name!=null && email !=null){
            textView5.text = name
            textView6.text = email
        }


//        circleImageView



        getProfileInfo()
    }
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        if (fragments == 0) {

           val fragment = supportFragmentManager.findFragmentByTag("mainfrag")
            if(fragment==null)
            {
                val frag : FragmentMain? = FragmentMain()
                supportFragmentManager.beginTransaction().replace(R.id.drawercontainer,frag,"mainfrag").addToBackStack(null).commit()
            }
            else
            {
                finish()
            }

        } else {
            val fragment1 = supportFragmentManager.findFragmentByTag("mainfrag")
            if(fragment1 !=null)
            {
                finish()
                return
            }


            if (fragmentManager.backStackEntryCount > 1) {
                fragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }
    private fun getProfileInfo() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getShowProfile(Hawk.get<Int>("id").toString()).enqueue(object : Callback<ResponseShowProfile> {
            override fun onFailure(call: Call<ResponseShowProfile>?, t: Throwable?) {

                Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseShowProfile>?, response: Response<ResponseShowProfile>?) {

                if(response!!.body()!!.isSuccess)
                {
                    var lname = ""
                    val fname = response.body()!!.payLoad.firstName
                    if(response.body()!!.payLoad.lastName !=null) {
                        lname = response.body()!!.payLoad.lastName!!
                    }
                    textView5.text = fname + lname
                    textView6.text = response.body()!!.payLoad.email

                    Picasso.get().load(response.body()!!.payLoad.image).placeholder(R.drawable.salman).into(circleImageView);
                }


            }
        })

    }
}
