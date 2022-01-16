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
    private val todoList = ArrayList<ToDoData>(); //데이터 저장 리스트
    private lateinit var writeDialog : Dialog //작성하는 dialog 화면

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setDateAndWeek() //날짜 및 요일 설정
        setStatusBarColor(R.color.main_color) //statusbar 색상 설정

        //첫 시작의 경우 data에 아무 것도 들어있지 않기 때문에 임시 데이터를 넣어줬습니다.
        todoList.add(ToDoData("Empty", "There is no data", false))

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

    private fun saveData(title: String, contents: String){ //입력받은 주제와 내용을 리스트에 저장
        todoList.add(ToDoData(title, contents, false))
        binding.rvTodo.adapter!!.notifyDataSetChanged()
    }

    private fun showWriteDialog(context: Context) {
        writeDialog.show()

        val writeTitle = writeDialog.findViewById<EditText>(R.id.writeTitle)
        val writeContents = writeDialog.findViewById<EditText>(R.id.writeContents)

        writeDialog.findViewById<Button>(R.id.saveBtn).setOnClickListener{
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            val title = writeTitle.text.toString()
            val contents = writeContents.text.toString()

            writeTitle.setText("")
            writeContents.setText("")

            saveData(title, contents) //입력받은 주제와 내용을 리스트에 저장

            writeDialog.dismiss()
        }

        writeDialog.findViewById<Button>(R.id.cancelBtn).setOnClickListener{
            Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()
            writeTitle.setText("")
            writeContents.setText("")
            writeDialog.dismiss()
        }
    }

    private fun setDateAndWeek(){ //날짜 및 요일 설정
        val currentTime = Calendar.getInstance().time
        val weekdayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())

        binding.week.text = weekdayFormat.format(currentTime)
        binding.date.text = dayFormat.format(currentTime)+"th"
    }

    private fun setStatusBarColor(color: Int){ //statusbar 색상 설정
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, color));
    }
}