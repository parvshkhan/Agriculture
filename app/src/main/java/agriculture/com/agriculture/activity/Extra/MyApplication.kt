package agriculture.com.agriculture.activity.Extra

import agriculture.com.agriculture.R
import android.app.Application
import com.orhanobut.hawk.Hawk
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



/**
 * Created by android on 29/9/18.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(applicationContext).build();
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/headingbrew.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}