package agriculture.com.agriculture.activity.retrofit

import agriculture.com.agriculture.activity.modelresponse.PropertyListSub
import agriculture.com.agriculture.activity.activ.SubListingActivity
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
         if (ctx is SubListingActivity)
        {
            resultDataCallback = ctx
        }
    }




    fun getSubListData(id: Int) {
        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getSubFarmList(id.toString()).enqueue(object : Callback<PropertyListSub> {
            override fun onFailure(call: Call<PropertyListSub>?, t: Throwable?) {
                resultDataCallback!!.apiresponse(t!!)
            }

            override fun onResponse(call: Call<PropertyListSub>?, response: Response<PropertyListSub>?) {
                resultDataCallback!!.apiresponse(response!!)
            }
        })
    }



}

