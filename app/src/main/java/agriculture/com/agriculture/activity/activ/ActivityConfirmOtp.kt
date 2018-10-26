package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.ForgetPasswordResponse
import agriculture.com.agriculture.activity.modelresponse.OtpMatchResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirm_otp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityConfirmOtp : AppCompatActivity() {

    var id : String ? = null
    var email : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_otp)

        if(intent!=null)
        {
            id = intent.getStringExtra("userid")
            email = intent.getStringExtra("email")
        }


        btSubmitOtp.setOnClickListener {

            if(edOtp.text.toString().isNotEmpty())
            {

                checkOtp(edOtp.text.toString(),id)

            }else
            {
                edOtp.setError("Empty")

            }


        }
    }

    private fun checkOtp(otp: String, id: String?) {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getOtpMatch(id!!,otp).enqueue(object : Callback<OtpMatchResponse> {

            override fun onFailure(call: Call<OtpMatchResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<OtpMatchResponse>?, response: Response<OtpMatchResponse>?) {

                if(response!!.body()!!.isSuccess)
                {
                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()

                    finish()
                    startActivity(Intent(applicationContext,ActivityResetPassword::class.java).putExtra("userid", id).putExtra("email",email))
                }
                else
                {

                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
