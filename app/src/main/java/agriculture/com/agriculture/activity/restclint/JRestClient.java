package agriculture.com.agriculture.activity.restclint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import agriculture.com.agriculture.activity.Testing;
import agriculture.com.agriculture.activity.modelresponse.ProfileUpdateResponse.UpdateProfileResponse;
import agriculture.com.agriculture.activity.modelresponse.ResponseHowItworks.HowItWorks;
import agriculture.com.agriculture.activity.modelresponse.ResponseUpdateProfile;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class JRestClient {

    public static GitApiInterface getClient() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder ()
                .setLenient()
                .create();

//        String ROOT_URL = "http://192.168.0.40/Agricultural_project/public/api/";
        String ROOT_URL = "http://smartit.ventures/farm/Agricultural_project/public/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .client ( client )
                .addConverterFactory( GsonConverterFactory.create(gson))
                .baseUrl(ROOT_URL)
                .build();


        GitApiInterface gitApiInterface = retrofit.create(GitApiInterface.class);

        return gitApiInterface;

    }

//    http://smartit.ventures/farm/Agricultural_project/public/api/updateProfile

    public interface GitApiInterface {
        @Multipart
        @POST("updateProfile")
        Call<ResponseUpdateProfile>  updateProfile(@Part("id")RequestBody id, @Part("firstName") RequestBody fName, @Part("mobile") RequestBody mobile, @Part("image\"; filename=\"pp.png\" ") RequestBody file, @Part("lastName") RequestBody Lname);



        @POST("getContentByParticularId")
        Call<Testing> getHowitWorks(@Query("id") String s);


                                                                                                        //id,firstName,mobile,image,lastName
    }


}
