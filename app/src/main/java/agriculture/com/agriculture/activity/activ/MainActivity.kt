package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Extra.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import spencerstudios.com.bungeelib.Bungee
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : BaseActivity() {
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSignIn.setOnClickListener {
            clickLogin()
        }

        btLogin.setOnClickListener {

            clickSignUp()
        }



    }

    private fun clickLogin() {

        val intent: Intent?
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        Bungee.shrink(this);  //fire the zoom animation


    }

    private fun clickSignUp() {

        val intent: Intent?
        intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        Bungee.shrink(this);  //fire the zoom animation
    }
}
