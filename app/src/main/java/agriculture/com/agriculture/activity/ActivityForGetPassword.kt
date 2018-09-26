package agriculture.com.agriculture.activity

import agriculture.com.agriculture.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_for_get_password.*

class ActivityForGetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_get_password)
        btForgetPass.setOnClickListener( {
        finish()
        })

        imgcloseforgetpassword.setOnClickListener( {
            finish()
        })
    }
}
