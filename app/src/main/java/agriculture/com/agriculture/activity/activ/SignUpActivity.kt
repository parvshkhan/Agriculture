package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Extra.BaseActivity
import agriculture.com.agriculture.activity.modelresponse.SignUpResponse
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
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    private var isCheckedOne = false
    private var isCheckedTwo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btRegister.setOnClickListener {
            signUpUser();
        }
        imgcloseforgetpassword.setOnClickListener {
            finish()
        }

        rguserPassword.isLongClickable = false
        rgusercfmPassword.isLongClickable =false

        rguserPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= rguserPassword.getRight() - rguserPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isCheckedOne) {
                        // show password

                        rguserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isCheckedOne = true

                        rguserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)



                    } else {
                        // hide password

                        rguserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isCheckedOne = false

                        rguserPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)


                    }
                    return@OnTouchListener true
                }
            }
            false
        })
        rgusercfmPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= rgusercfmPassword.getRight() - rgusercfmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isCheckedOne) {
                        // show password

                        rgusercfmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isCheckedOne = true

                        rgusercfmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)



                    } else {
                        // hide password

                        rgusercfmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isCheckedOne = false

                        rgusercfmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)
                    }
                    return@OnTouchListener true
                }
            }
            false
        })

    }

    private fun signUpUser() {

        rgusername.setError(null)
        rgemail.setError(null)
        rguserPassword.setError(null)
        rgusercfmPassword.setError(null)



        if (rgusername.text.isNotEmpty() && rgemail.text.isNotEmpty() && rguserPassword.text.isNotEmpty())
        {
            if(rguserPassword.text.toString() == rgusercfmPassword.text.toString())
            signUpwithUserNamePassword(rgusername.text.toString(),rgemail.text.toString(),rguserPassword.text.toString())
            else{
                rguserPassword.setError("Not match")
                rgusercfmPassword.setError("Not match")
            }
        }
        else
        {
            if(rgusername.text.isEmpty())
            {
                rgusername.setError("Empty")
            }
            else
            {
                rgusername.setError(null)
            }
            if(rgemail.text.isEmpty())
            {
                rgemail.setError("Empty")
            }else
            {
                rgemail.setError(null)
            }
            if(rguserPassword.text.isEmpty())
            {

                rguserPassword.setError("Empty")
            }else
            {
                rguserPassword.setError(null)
            }



        }


    }

    private fun signUpwithUserNamePassword(username: String, email: String, password: String) {


        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getSignUpResponse(username,email,password).enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>?, t: Throwable?) {
Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpResponse>?, response: Response<SignUpResponse>?) {
                if(response!!.body()!!.isSuccess)
                {
                    Hawk.put<Boolean>("isFirst",false)
                    Hawk.put("name",response.body()!!.payLoad.firstName)
                    Hawk.put("email",response.body()!!.payLoad.email)
                    Hawk.put("id",response.body()!!.payLoad.id)

                    var intent : Intent? = null
                    intent = Intent(applicationContext, DrawerActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else
                {

                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            }
        })





    }
}
