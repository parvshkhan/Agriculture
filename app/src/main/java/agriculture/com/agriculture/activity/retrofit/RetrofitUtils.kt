package agriculture.com.agriculture.activity.retrofit

import agriculture.com.agriculture.activity.DrawerActivity
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitUtils(ctx: Context) {

    var resultDataCallback : ICallback ? = null;

    init {
        if (ctx is DrawerActivity)
        {
            resultDataCallback = ctx as ICallback
        }
    }

     fun getListData() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getFarmList().enqueue(object : Callback<PropertyList> {
            override fun onFailure(call: Call<PropertyList>?, t: Throwable?) {
                resultDataCallback!!.apiresponse(t!!)
            }

            override fun onResponse(call: Call<PropertyList>?, response: Response<PropertyList>?) {
                resultDataCallback!!.apiresponse(response!!)
            }
        })
    }
}

