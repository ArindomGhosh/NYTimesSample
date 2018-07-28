package test.com.nytimes.mostpopular.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.most_popular_item_list.view.*
import test.com.nytimes.R
import test.com.nytimes.mostpopular.model.MostPopularResult
import java.util.*

class MostPopularRecyclerViewAdapter(): RecyclerView.Adapter<MostPopularRecyclerViewAdapter.ViewHolder>() {
    private var totalList: List<MostPopularResult> = Collections.emptyList()
    private var itemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.most_popular_item_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = totalList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(totalList[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.setOnItemClickListener(totalList[position])
        }
    }

    fun setPopularList(results: List<MostPopularResult>) {
        this.totalList = results
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: MostPopularResult) {
            itemView.title.text = result.title
            itemView.byLine.text= result.byline
            itemView.publishedDate.text = result.published_date
        }
    }

    interface OnItemClickListener {
        fun setOnItemClickListener(mostPopularResult: MostPopularResult)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

}