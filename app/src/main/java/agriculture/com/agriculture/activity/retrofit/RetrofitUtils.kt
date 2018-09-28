package agriculture.com.agriculture.activity.retrofit

import agriculture.com.agriculture.activity.DrawerActivity
import agriculture.com.agriculture.activity.PropertyListSub
import agriculture.com.agriculture.activity.SubListingActivity
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.callback.ICallback
import agriculture.com.agriculture.activity.fragment.fragmentresources.FragmentBlogs
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import agriculture.com.agriculture.activity.modelresponse.ProprtyListingSubListing
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
            resultDataCallback = ctx
        }else if (ctx is SubListingActivity)
        {
            resultDataCallback = ctx
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
     fun getBlogList() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getBlogList().enqueue(object : Callback<BlogResponse> {
            override fun onFailure(call: Call<BlogResponse>?, t: Throwable?) {
                resultDataCallback!!.apiresponse(t!!)
            }

            override fun onResponse(call: Call<BlogResponse>?, response: Response<BlogResponse>?) {
                resultDataCallback!!.apiresponse(response!!)
            }
        })
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

