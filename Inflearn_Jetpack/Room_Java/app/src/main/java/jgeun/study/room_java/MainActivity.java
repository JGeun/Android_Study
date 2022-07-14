package jgeun.study.room_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries() //MainThread에서 db를 저장해도 괜찮다는 뜻 (실무에선 X - Back에서 돌아가게 함)
                .build();

        // UI 갱신
        db.todoDao().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // 버튼 클릭 시 DB에 insert
        findViewById(R.id.add_button).setOnClickListener(v -> {
            db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
        });
    }
}