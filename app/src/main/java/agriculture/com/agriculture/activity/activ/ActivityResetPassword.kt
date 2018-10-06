package agriculture.com.agriculture.activity.activ

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.modelresponse.ResetPasswordResponse
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reset_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityResetPassword : AppCompatActivity() {

    var email :  String ? = null
    var id : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        if(intent!=null)
        {
            id = intent.getStringExtra("userid")
            email = intent.getStringExtra("email")
        }

        btsubmitResetpassword.setOnClickListener {

            if(rscfmpassword.text.toString().isNotEmpty() && rspassword.text.toString().isNotEmpty())
            {
                if(rspassword.text.toString().equals(rscfmpassword.text.toString()))
                {
                    updatePassword(email,id)
                }
            }
            else
            {
                if(rscfmpassword.text.toString().isEmpty())
                {
                    rscfmpassword.setError("Empty")
                }
                if(rspassword.text.toString().isEmpty())
                {
                    rspassword.setError("Empty")
                }
                else if(!rscfmpassword.text.toString().equals(rspassword.text.toString()))
                {
                    rspassword.setError("Password not match")
                }
            }
        }
    }

    private fun updatePassword(email: String?, id: String?) {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getResetOtp(email!!,id!!).enqueue(object : Callback<ResetPasswordResponse> {
            override fun onFailure(call: Call<ResetPasswordResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext,t.toString(),Toast.LENGTH_SHORT).show()


            }

            override fun onResponse(call: Call<ResetPasswordResponse>?, response: Response<ResetPasswordResponse>?) {
                if(response!!.body()!!.isSuccess)
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                }
            }
        })







    }
}
