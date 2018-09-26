package agriculture.com.agriculture.activity.fragment.fragmentsublisting


import agriculture.com.agriculture.R
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


class FragmentOverview : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment__over_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val series = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(DataPoint(0.0, 1.0), DataPoint(1.0, 5.0), DataPoint(2.0, 3.0), DataPoint(3.0, 2.0), DataPoint(4.0, 6.0)))
        graphView.addSeries(series)

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
        graphView.getViewport().setScalable(true);
        graphView.gridLabelRenderer.setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
        series.isDrawDataPoints = true
        series.dataPointsRadius = 10f
        series.setAnimated(true)
        tvremainAmount.text = Html.fromHtml("<strong><font color=black>$128000</font></strong> left from <strong><font color=black>$200000</font></strong>")

    }

}
