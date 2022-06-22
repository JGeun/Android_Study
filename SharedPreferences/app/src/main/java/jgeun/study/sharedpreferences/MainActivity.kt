package jgeun.study.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jgeun.study.sharedpreferences.databinding.ActivityMainBinding

/*
* 화면이 꺼졌을 때 SharedPreferences(내부저장소)를 이용하여 저장하는 방식입니다.
*/
class MainActivity : AppCompatActivity() {

    companion object {
        const val SF_KEY = "sf_key"
        const val SF_CONTENT = "sf_content"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*
        *  SharedPreferences를 SF_KEY 통해 생성
        *  MODE_PRIVATE: 기본 모드, 생성된 파일은 오직 호출하는 어플리케이션에 의해 접근되어야만 사용 가능
        *  MODE_WORLD_READABLE: 생성된 파일에 읽기 권한을 가진 모든 다른 어플리케이션을 허락 (Deprecated)
        *  MODE_WORD_WRITEABLE: 생성된 파일에 쓰기 권한을 가진 모든 다른 어플리케이션을 허락 (Deprecated)
        *  MODE_APPEND: openFileOutput과 함께 사용,
        *         만약 파일이 존재한다면 데이터를 지우는 것 대신 기존의 데이터 끝에 데이터를 추가합니다.
        *  MODE_MULTI_PROCESS: 디스크에 있는 파일은 이미 인스턴스가 프로세스에 load되어 있다고 하더라도 수정을 위해 체크될 것이다.
        *                    이 행동은 어플리케이션가 멀티스레드를 가지고 있을 때 필요해짐.
        *                    몇 버전에선 작동하지 않는다. (Deprecated)
        */
        sf = getSharedPreferences(SF_KEY, MODE_PRIVATE)
        editor = sf.edit()
    }

    override fun onPause() {
        super.onPause()

        // SF_CONTENT key에 editText에 입력한 메세지를 저장
        val content = binding.etContent.text.toString()
        editor.apply {
            putString(SF_CONTENT, content)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()

        // SF_CONTENT key에 저장된 string데이터를 불러와 textView에 입력
        val content = sf.getString(SF_CONTENT, null)
        binding.tvStore.text = content
    }
}