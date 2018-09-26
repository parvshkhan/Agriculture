package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_signup.*

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





        userPassword.isLongClickable = false
        usercfmPassword.isLongClickable =false



        userPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= userPassword.getRight() - userPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isCheckedOne) {
                        // show password

                        userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isCheckedOne = true

                        userPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)



                    } else {
                        // hide password

                        userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isCheckedOne = false

                        userPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)


                    }
                    return@OnTouchListener true
                }
            }
            false
        })


        usercfmPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= usercfmPassword.getRight() - usercfmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isCheckedOne) {
                        // show password

                        usercfmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isCheckedOne = true

                        usercfmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)



                    } else {
                        // hide password

                        usercfmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isCheckedOne = false

                        usercfmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)
                    }
                    return@OnTouchListener true
                }
            }
            false
        })













    }

    private fun signUpUser() {

        finish()
        var intent : Intent? = null
        intent = Intent(this,DrawerActivity::class.java)
        startActivity(intent)

    }
}
