package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import agriculture.com.agriculture.R.id.imgBackRegister
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

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


        edPassword.setLongClickable(false)

        edPassword.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= edPassword.getRight() - edPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    if (!isChecked) {
                        // show password

                        edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        isChecked = true

                        edPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_hide, 0)




                    } else {
                        // hide password

                        edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        isChecked = false

                        edPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_eyepassword, 0)
                    }
                    return@OnTouchListener true
                }
            }
            false
        })




    }

    private fun loginUser() {

        finish()
        var intent : Intent? = null
        intent = Intent(this,DrawerActivity::class.java)
        startActivity(intent)
    }
}
