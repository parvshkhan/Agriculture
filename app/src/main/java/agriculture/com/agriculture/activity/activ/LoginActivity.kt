package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Extra.BaseActivity
import agriculture.com.agriculture.activity.modelresponse.LoginResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class LoginActivity : BaseActivity() {

    private var isChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            loginUser();
        }

        imgBackLogin.setOnClickListener {
            finish()
        }

        tvforgotpassword.setOnClickListener {
          startActivity(Intent(this, ActivityForGetPassword::class.java))
        }


        lgpassword.setLongClickable(false)

        lgpassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= lgpassword.getRight() - lgpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isChecked) {
                        // show password

                        lgpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isChecked = true

                        lgpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)




                    } else {
                        // hide password

                        lgpassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isChecked = false

                        lgpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)
                    }
                    return@OnTouchListener true
                }
            }
            false
        })




    }

    private fun loginUser() {


        if(lgemail.text.isNotEmpty()  && lgpassword.text.isNotEmpty())
        {
            if(isEmailValid(lgemail.text.toString()))
            loginwithUserNamePassword(lgemail.text.toString(),lgpassword.text.toString())
            else
                lgemail.setError("email not valid")

        }else
        {

            if(lgemail.text.isEmpty())
                lgemail.setError("Empty")
            if(lgpassword.text.isEmpty())
                lgpassword.setError("Empty")

        }

    }

    private fun loginwithUserNamePassword(email: String?, password: String?) {


        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getLoginResponse(email!!,password!!).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                if(response!!.body()!!.isSuccess)
                {
                    Hawk.put("name",response.body()!!.payLoad.firstName)
                    Hawk.put("email",response.body()!!.payLoad.email)
                    Hawk.put("id",response.body()!!.payLoad.id)
                    Hawk.put<Boolean>("isFirst",false)
                    var intent : Intent? = null
                    intent = Intent(applicationContext, DrawerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                }


            }
        })
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
