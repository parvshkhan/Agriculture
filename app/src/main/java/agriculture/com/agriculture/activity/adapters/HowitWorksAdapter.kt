package agriculture.com.agriculture.activity.adapters

import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.model.ModelQuestionAnswer
import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.row_drawer_items.view.*
import java.util.HashMap


class CustomExpandableListAdapter(private val context: Context, private val expandableListTitle: List<String>,
                                  private val expandableListDetail: HashMap<String, List<ModelQuestionAnswer>>) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.expandableListDetail[this.expandableListTitle[listPosition]]?.get(expandedListPosition)!!
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as ModelQuestionAnswer

        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val tvquestions = convertView!!
                .findViewById<TextView>(R.id.expandedListItem)
        val tvanswers = convertView
                .findViewById<TextView>(R.id.expandedListItemanswer)



        tvquestions.setText(expandedListText.getQuestions())
        tvanswers.setText(Html.fromHtml(expandedListText.getAnswers()))


        tvquestions.setOnClickListener {
            var modelQuestion =    expandableListDetail.get(expandableListTitle[listPosition])


            /*if(modelQuestion!![expandedListPosition].isCollapsed)
            {
                modelQuestion[expandedListPosition].isCollapsed = !modelQuestion[0].isCollapsed
                tvanswers.visibility = View.VISIBLE
                val slideDown = AnimationUtils.loadAnimation(context, R.anim.dropdownanim)
                tvanswers.startAnimation(slideDown)
            }
            else
            {
                modelQuestion[expandedListPosition].isCollapsed = !modelQuestion[0].isCollapsed
                tvanswers.visibility = View.GONE
                val slideDown = AnimationUtils.loadAnimation(context, R.anim.dropdownanim)
                tvanswers.startAnimation(slideDown)
            }*/

        }


        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.expandableListDetail[this.expandableListTitle[listPosition]]?.size!!
    }

    override fun getGroup(listPosition: Int): Any {
        return this.expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String

        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.listTitle)

        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
       
        
       
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
    
    
    
    
}