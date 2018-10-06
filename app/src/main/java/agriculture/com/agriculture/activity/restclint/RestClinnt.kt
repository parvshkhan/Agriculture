package agriculture.com.agriculture.activity.restclint
import agriculture.com.agriculture.activity.activ.PropertyListSub
import agriculture.com.agriculture.activity.modelresponse.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class RestClinnt {

    companion object Factory {
        val ROOT_URL = "http://192.168.0.40/Agricultural_project/public/api/"
//        val ROOT_URL = "http://smartit.ventures/farm/Agricultural_project/public/api/"
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

    @POST("getEventByParticularId")
    fun getSubEventList(@Query("id") id: String): Call<EventSubLIstingResponse>

    @POST("getAllBlogList")
    fun getBlogList(): Call<BlogResponse>

    @POST("getAllEventList")
    fun getEventList(): Call<EventResponse>

    @POST("signin")
    fun getLoginResponse(@Query("email") email: String,@Query("password") password: String): Call<LoginResponse>

    @POST("signup")
    fun getSignUpResponse(@Query("first_name") username: String,@Query("email") email: String,@Query("password") password: String): Call<SignUpResponse>


    @POST("forgot-password")
    fun getForgetPassword(@Query("email") email : String): Call<ForgetPasswordResponse>

    @FormUrlEncoded
    @POST("matchOTP")
    fun getOtpMatch(@Field("userId") userid : String, @Field("otp") otp : String): Call<OtpMatchResponse>


    @FormUrlEncoded
    @POST("reset-password")
    fun getResetOtp(@Field("email") email : String, @Field("password") password : String): Call<ResetPasswordResponse>


    @POST("showProfile")
    fun getShowProfile(@Query("id") id : String): Call<ResponseShowProfile>

    @Multipart
    @POST("UpdateProfile")
     fun updateProfile( @Part("id") id: RequestBody,@Part("firstName") fName: RequestBody,@Part("image\"; filename=\"pp.png\" ") file: RequestBody, @Part("mobile") mobile: RequestBody): Call<ResponseUpdateProfile>


}