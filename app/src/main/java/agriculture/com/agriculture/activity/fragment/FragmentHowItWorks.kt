package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.model.ModelQuestionAnswer
import agriculture.com.agriculture.activity.modelresponse.ResponseHowItworks.HowItWorks
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import kotlin.collections.ArrayList



class FragmentHowItWorks : Fragment() {

    var expandableListTitle: MutableList<String>? = null
    var expandableListDetail: HashMap<String, List<ModelQuestionAnswer>>? =   null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_it_works, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id =arguments!!.getString("id")



//        getHowitWorks(id)






    }

   /* fun getHowitWorks(id: String) {
        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getHowitWorks(id.toString()).enqueue(object : Callback<HowItWorks> {
            override fun onFailure(call: Call<HowItWorks>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<HowItWorks>?, response: Response<HowItWorks>) {

                if(response.body()!!.isSuccess)
                {
                    for(i in 0..response.body()!!.payLoad.heading.size-1)
                    {
                        expandableListTitle!!.add(response.body()!!.payLoad.heading[i].title)
                    }



                }










                expandableListTitle = ArrayList(expandableListDetail!!.keys)



            }
        })
    }*/


}
