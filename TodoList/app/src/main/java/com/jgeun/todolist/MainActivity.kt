package com.jgeun.todolist

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jgeun.todolist.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

data class ToDoData(var title: String, var contents: String, var isDone: Boolean)

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val todoList = ArrayList<ToDoData>();
    private lateinit var writeDialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setDateAndWeek()
        setStatusBarColor(R.color.main_color)

        todoList.add(ToDoData("Empty", "There is no data", false))
        println("size: " + todoList.size)

        binding.rvTodo.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(this@MainActivity, todoList)
        }


        writeDialog = Dialog(this)
        writeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        writeDialog.setContentView(R.layout.dialog_write)

        binding.addTodo.setOnClickListener{
            showWriteDialog(this)
        }
    }

    fun saveData(title: String, contents: String){
        todoList.add(ToDoData(title, contents, false))
        binding.rvTodo.adapter!!.notifyDataSetChanged()
    }

    private fun showWriteDialog(context: Context) {
        writeDialog.show()


        writeDialog.findViewById<Button>(R.id.saveBtn).setOnClickListener{
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            val title = writeDialog.findViewById<EditText>(R.id.writeTitle).text.toString()
            val contents = writeDialog.findViewById<EditText>(R.id.writeContents).text.toString()
            saveData(title, contents)
            writeDialog.dismiss()
        }

        writeDialog.findViewById<Button>(R.id.cancelBtn).setOnClickListener{
            Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()
            writeDialog.dismiss()
        }
    }

    private fun setDateAndWeek(){
        val currentTime = Calendar.getInstance().time
        val weekdayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())

        binding.week.text = weekdayFormat.format(currentTime)
        binding.date.text = dayFormat.format(currentTime)+"th"
    }
    private fun setStatusBarColor(color: Int){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, color));
    }
}