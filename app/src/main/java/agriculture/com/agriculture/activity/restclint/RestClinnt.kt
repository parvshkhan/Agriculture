package agriculture.com.agriculture.activity.restclint
import agriculture.com.agriculture.activity.modelresponse.PropertyList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

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
}