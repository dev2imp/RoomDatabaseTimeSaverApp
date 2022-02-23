package com.developeros.timesaver.view.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developeros.howlongdoyouwork.AddWorkDialogListener
import com.developeros.howlongdoyouwork.RecyclewViewAdapter2
import com.developeros.timesaver.R
import com.developeros.timesaver.aplication.TimeSaverApp
import com.developeros.timesaver.model.TimeManipulation
import com.developeros.timesaver.viewmodel.ViewModelClass
import org.json.JSONObject

class ExapandedWorkFragment : Fragment(), View.OnClickListener{
    private lateinit var  viewModelClass: ViewModelClass
    lateinit var recyclerView: RecyclerView
    lateinit var recyclewViewAdapter2 :RecyclewViewAdapter2
    lateinit var backArrow:ImageView
    lateinit var addWorkDialogListener: AddWorkDialogListener
    var key:String=""
    var ItemsArryList:ArrayList<ArrayList<String>> = ArrayList()
    var timeManipulation = TimeManipulation()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View?=null
        view=inflater!!.inflate(R.layout.fragment_workexpanded,container,false)
        key= requireArguments().getString(getString(R.string.DataFomActivityToFragment),"")
        if(key.trim().length>1){
            //StringtoArrayList(view)
            backArrow=view.findViewById(R.id.BackArrow)
            recyclerView=view.findViewById(R.id.RecView2)
            backArrow.setOnClickListener(this)
            iniRecView()
            viewModelClass= ViewModelClass((requireActivity().application as TimeSaverApp).repository)
            viewModelClass.allTasks.observe(requireActivity(), Observer {
                recyclewViewAdapter2.addData(it)
            })

        }
        return view
    }
    private fun iniRecView(){
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclewViewAdapter2= RecyclewViewAdapter2(requireContext())
        recyclerView.adapter = recyclewViewAdapter2
    }
    override fun onClick(v: View?) {
        if(v!!.id==R.id.BackArrow){
            addWorkDialogListener=context as AddWorkDialogListener
            addWorkDialogListener.RemooveFragment()
        }
    }
}

