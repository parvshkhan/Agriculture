package agriculture.com.agriculture.activity.restclint
import agriculture.com.agriculture.activity.PropertyListSub
import agriculture.com.agriculture.activity.modelresponse.BlogResponse
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import agriculture.com.agriculture.activity.modelresponse.ProprtyListingSubListing
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query

class RestClinnt {

    companion object Factory {
        val ROOT_URL = "http://192.168.0.40/Agricultural_project/public/api/"
        fun <T> create(java: Class<WikiApiService>): WikiApiService {
            val retrofit = Retrofit.Builder()
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ROOT_URL)
                    .build()

            return retrofit.create(WikiApiService::class.java);
        }
    }


}


interface WikiApiService {
    @POST("getAllPropertyList")
    fun getFarmList() : Call<PropertyList>

    @POST("getPropertyByParticularId")
    fun getSubFarmList(@Query("id") id: String): Call<PropertyListSub>

    @POST("getAllBlogList")
    fun getBlogList(): Call<BlogResponse>
}