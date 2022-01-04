package com.developeros.timesaver.view.Activities
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developeros.howlongdoyouwork.*
import com.developeros.timesaver.R
import com.developeros.timesaver.R.string.*
import com.developeros.timesaver.aplication.TimeSaverApp
import com.developeros.timesaver.model.AppSaveDataModel
import com.developeros.timesaver.model.TimeDateModel
import com.developeros.timesaver.view.Fragments.ExapandedWorkFragment
import com.developeros.timesaver.viewmodel.ViewModelClass
import org.json.JSONObject
class MainActivity : AppCompatActivity(),
        View.OnClickListener,
    AddWorkDialogListener {
    private lateinit var  viewModelClass:ViewModelClass
    lateinit var recyclerView:RecyclerView
    lateinit var recyclewViewAdapter:RecyclewViewAdapter
    lateinit var addItem : ImageView
    lateinit var mainRelativeLayout:RelativeLayout
    var deleteIndex: Int = -1
    var newTask: AddNewTaskDialog = AddNewTaskDialog()
    var deleteWorkDialog = DeleteWorkDialog()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addItem = findViewById(R.id.Add)
        recyclerView=findViewById(R.id.RecView)
        mainRelativeLayout=findViewById(R.id.mainRelativeLayout)
        addItem.setOnClickListener(this)
        viewModelClass= ViewModelClass((application as TimeSaverApp).repository)
        viewModelClass.allTasks.observe(this, Observer {
            recyclewViewAdapter.newItem(it)
        })
        iniRecView()
    }
    private fun iniRecView(){
        recyclewViewAdapter=RecyclewViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclewViewAdapter
    }
    fun SaveTimeNow(){
        var timeDateModel  = TimeDateModel()
        var  prefences = this.getSharedPreferences(getString(TIME_DATE_MODEL), Context.MODE_PRIVATE)
        prefences.edit().putString(getString(TIME_DATE_MODEL),timeDateModel.toString()).apply()
    }
    fun GetTimeFromPref(){
    /*
    }
        var  prefences = getSharedPreferences(getString(TIME_DATE_MODEL),
            Context.MODE_PRIVATE)
        val time= prefences.getString(getString(TIME_DATE_MODEL),"")
        var string = time.toString()
        string = string.replace("=",":")
        string = string.replace("TimeDateModel(","{")
        string = string.replace(")","}")
        var jsonObject = JSONObject(string)
        var year = jsonObject.getString(getString(R.string.year))
        var month = jsonObject.getString(getString(R.string.month))
        var day = jsonObject.getString(getString(R.string.day))
        var hour = jsonObject.getString(getString(R.string.hour))
        var minute = jsonObject.getString(getString(R.string.minute))
        var timeManipulation = TimeManipulation()
        var before= timeManipulation.DateTimeToSecond(year,month,day,hour,minute)
        var timeDateModel  = TimeDateModel()//gettin time right now
        year = timeDateModel.year.toString()
        month = timeDateModel.mont.toString()
        day = timeDateModel.day.toString()
        hour = timeDateModel.hour.toString()
        minute = timeDateModel.minute.toString()
        var now= timeManipulation.DateTimeToSecond(year,month,day,hour,minute)
        var work = dataToDisk.UserHashData.get(dataToDisk.UserData.get(index).W)?.get(0)!!.Work
        var running = dataToDisk.UserHashData.get(dataToDisk.UserData.get(index).W)?.get(0)!!.Running
        var totalWork=(now-before).toString()
        if(now-before > 0){
            saveDataModel= SaveDataModel()
            saveDataModel.DataToSave(year,month,day,hour, minute, totalWork, running, work)
*/
    }
    override fun onClick(v: View?){
        if(v?.id  == R.id.Add){
            newTask.show(supportFragmentManager,"Dialog")
        }
    }
    override fun onDialogPositiveClick(string: String) {
        var appSaveDataModel =AppSaveDataModel(string,"0","0")
        viewModelClass.insert(appSaveDataModel)
    }
    override fun onDialogPositiveClick(int: Int){
        //this function is called when user clicks delete and when clicks ok to delete
        //that is why I set deletedIndex to -1 initially then set it to index that was clicked
        //if user agree with delete the item will be removed
        if(deleteIndex==-1){
            //display dialog
            deleteIndex=int
            deleteWorkDialog.show(supportFragmentManager,"Dialog")
        }else{
            viewModelClass.delete(deleteIndex)
            deleteIndex=-1
        }
    }
    override fun onDialogPositiveClick(int: Int, running: Boolean){
        viewModelClass.RunOrStop(int,running)
        //viewModelClass.RunOrStop(int,running)
    }
    override fun onDialogNegativeClick(string: String) {
        deleteIndex=-1
    }
    override fun ExpandMore(key: String) {
        mainRelativeLayout.visibility=View.INVISIBLE
        var fragment = ExapandedWorkFragment()
        var bundle = Bundle()
        bundle.putString(getString(DataFomActivityToFragment),
                key)
        fragment.arguments=bundle
        supportFragmentManager!!.beginTransaction().
                add(R.id.FragmentContainer,fragment,"Fragment").commit()
    }
    override fun RemooveFragment() {
        var fragment: Fragment = supportFragmentManager?.
           findFragmentById(R.id.FragmentContainer)!!
           supportFragmentManager?.
            beginTransaction()?.remove(fragment)?.commit()
        mainRelativeLayout.visibility=View.VISIBLE
    }
}