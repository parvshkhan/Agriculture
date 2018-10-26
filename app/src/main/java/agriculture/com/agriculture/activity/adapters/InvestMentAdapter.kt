package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.fragment.FragmentInvestmentSub
import agriculture.com.agriculture.activity.modelresponse.InvestmentDetailResponse
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_investment_plan.view.*


class InvestMentAdapter(evenlist: InvestmentDetailResponse) : RecyclerView.Adapter<InvestMentAdapter.MyViewHolder>() {

    var dataList :InvestmentDetailResponse? = evenlist
    var context : Context? = null

    override fun getItemCount(): Int {
        return dataList!!.payload.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_investment_plan,parent,false))
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.textView25.text=dataList!!.payload.get(position).title
        holder.itemView.textView29.text= Html.fromHtml(dataList!!.payload.get(position).description)
        var id = dataList!!.payload.get(position).id
        holder.itemView.button4.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("id", id.toString())
            val fragment = FragmentInvestmentSub()
            fragment.arguments = bundle
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().add(R.id.drawercontainer,fragment,"fragwebview").addToBackStack(null).commit()

        }

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {




    }



}