package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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

        var intent: Intent?
        intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)



    }

    private fun clickSignUp() {

        var intent: Intent?
        intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)

    }
}
