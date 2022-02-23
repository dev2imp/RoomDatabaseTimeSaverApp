package com.developeros.howlongdoyouwork

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.developeros.timesaver.R
import com.developeros.timesaver.model.AppSaveDataModel

class RecyclewViewAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private var Items:List<AppSaveDataModel> = ArrayList()
    var context: Context
    constructor(context:Context) : super() {
        this.context= context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_inner_recview,parent,false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RecViewHolder ->{
                holder.bind(context,Items.get(position),position)
            }
        }
    }
    override fun getItemCount(): Int {
        return Items.size
    }
    class RecViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(context:Context,values: AppSaveDataModel,position: Int){
            val date :TextView = itemView.findViewById(R.id.Date)
            val time :TextView = itemView.findViewById(R.id.Time)
            val totalWork :TextView = itemView.findViewById(R.id.TotalWork)
            //day/monnth/year
            date.setText(values.Day+"/"+values.Month+"/"+values.Year)
            //hour:minute:second
            time.setText(values.Hour+":"+values.Minute+":00")
            totalWork.setText(values.totalWork)
        }
    }
    fun addData(arr:List<AppSaveDataModel>){
        Items=arr
    }
}