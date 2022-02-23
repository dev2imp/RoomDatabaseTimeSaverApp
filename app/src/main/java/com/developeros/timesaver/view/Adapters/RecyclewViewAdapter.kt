package com.developeros.howlongdoyouwork

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.developeros.timesaver.R
import com.developeros.timesaver.model.AppSaveDataModel

class RecyclewViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private var Items:List<AppSaveDataModel> = ArrayList();
    var context: Context
    constructor(context:Context) : super() {
        this.context= context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_recview,parent,false)
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

        fun bind(context:Context,keyDataModel: AppSaveDataModel,position: Int){
            val work :TextView = itemView.findViewById(R.id.Work)
            val ItemRelLayout :RelativeLayout = itemView.findViewById(R.id.itemRelLayout)
            val deleteTask : ImageView =itemView.findViewById(R.id.deleteItem)
            val PlayStop: ImageView = itemView.findViewById(R.id.StartStop)
            lateinit var addWorkDialogListener: AddWorkDialogListener
            work.setText(keyDataModel.Work)
            if(keyDataModel.Running.toBoolean()){
                PlayStop.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_play_circle_24))
            }else{
                PlayStop.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_stop_circle_24))

            }
            ItemRelLayout.setOnClickListener(){

                addWorkDialogListener=context as AddWorkDialogListener
                addWorkDialogListener.ExpandMore(keyDataModel.Work)
            }
            deleteTask.setOnClickListener(){
                    addWorkDialogListener=context as AddWorkDialogListener
                    addWorkDialogListener.onDialogPositiveClick(position)
                }
            PlayStop.setOnClickListener (){
                addWorkDialogListener=context as AddWorkDialogListener
                addWorkDialogListener.onDialogPositiveClick(position,!keyDataModel.Running.toBoolean())
            }
        }

    }
    fun newItem(keyDataModel: List<AppSaveDataModel>){
        Items=keyDataModel
        notifyDataSetChanged()
    }
}