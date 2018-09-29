package agriculture.com.agriculture.activity

import android.app.Application
import com.orhanobut.hawk.Hawk

/**
 * Created by android on 29/9/18.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(applicationContext).build();
    }
}