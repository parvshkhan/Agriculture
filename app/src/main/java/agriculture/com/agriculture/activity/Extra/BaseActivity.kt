package agriculture.com.agriculture.activity.Extra

import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by android on 14/9/18.
 */
open class BaseActivity : AppCompatActivity() {


    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()


    }



}