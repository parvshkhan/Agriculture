package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.ForgetPasswordResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_for_get_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityForGetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_get_password)

        btForgetPass.setOnClickListener {

            if(edEmailforgetpassword.text.toString().isNotEmpty())
            {
                sendRequest(edEmailforgetpassword.text.toString())
            }
            else
            {
                edEmailforgetpassword.setError("Empty")
            }
        }

        imgcloseforgetpassword.setOnClickListener {
            finish()
        }
    }

    private fun sendRequest(email: String) {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getForgetPassword(email).enqueue(object : Callback<ForgetPasswordResponse> {
            override fun onFailure(call: Call<ForgetPasswordResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ForgetPasswordResponse>?, response: Response<ForgetPasswordResponse>?) {


                if(response!!.body()!!.isSuccess)
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(Intent(applicationContext,ActivityConfirmOtp::class.java).putExtra("userid", response.body()!!.payLoad.id.toString()).putExtra("email",response.body()!!.payLoad.email.toString()))
                }
                else
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                }
            }
        })








    }
}
