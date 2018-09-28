package agriculture.com.agriculture.activity.fragment.fragmentsublisting


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.PropertyListSub
import agriculture.com.agriculture.activity.modelresponse.ProprtyListingSubListing
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment__over_view.*
import com.jjoe64.graphview.helper.StaticLabelsFormatter




class FragmentOverview : Fragment() {






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment__over_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<PropertyListSub>("data") as PropertyListSub
        progressBar.max = data.payLoad.totalFund
        progressBar.progress= data.payLoad.funded

        tvfunded.text = data.payLoad.funded.toString()
        tvroi.text = data.payLoad.roi.toString()
        tvdaysleft.text = data.payLoad.diffDate.toString()

        val horizontalLabel = arrayOfNulls<String>(data.payLoad.chart.size)
        val arrDataPoints = arrayOfNulls<DataPoint>(data.payLoad.chart.size)



        val staticLabelsFormatter = StaticLabelsFormatter(graphView)

        for (i in data.payLoad.chart.indices) {
            horizontalLabel.set(i, data.payLoad.chart[i].label.toString())
            arrDataPoints.set(i,DataPoint(data.payLoad.chart[i].label.toDouble(),data.payLoad.chart[i].y))
        }

        val series = LineGraphSeries<DataPoint>(arrDataPoints)

        staticLabelsFormatter.setHorizontalLabels(horizontalLabel)
        graphView.addSeries(series)
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        val mPaint = Paint()
        mPaint.setAntiAlias(true)
        mPaint.setDither(true)
        mPaint.setColor(resources.getColor(R.color.yellow))
        mPaint.setStyle(Paint.Style.STROKE)
        mPaint.setStrokeJoin(Paint.Join.ROUND)
        mPaint.setStrokeCap(Paint.Cap.ROUND)
        mPaint.setStrokeWidth(8f)
        series.setCustomPaint(mPaint)

        graphView.gridLabelRenderer.horizontalAxisTitleColor = android.R.color.black
        graphView.gridLabelRenderer.verticalAxisTitleColor = android.R.color.black
//        graphView.getViewport().setScalable(true);
        graphView.gridLabelRenderer.setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
        series.isDrawDataPoints = true
        series.dataPointsRadius = 10f
        series.setAnimated(true)
        tvremainAmount.text = Html.fromHtml("<strong><font color=black>$"+data.payLoad.funded+"</font></strong> raised of $<strong><font color=black>"+data.payLoad.totalFund+"</font></strong>")

    }

}
