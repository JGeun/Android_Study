package com.jgeun.kakaoclone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jgeun.kakaoclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBarColor(R.color.bg_login_color)

        loginBtnSetting()

        binding.loginBtn.setOnClickListener{
            login()
        }
        auth = Firebase.auth
    }

    private fun login() {
        auth.signInWithEmailAndPassword(binding.loginId.text.toString(), binding.loginPw.text.toString())
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인이 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                   Toast.makeText(this, "로그인이 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginBtnSetting() {
        binding.loginId.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                setBtnLogin()
            }

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {}
        })

        binding.loginPw.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                setBtnLogin()
            }

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun setBtnLogin() {
        if (loginValidation()) {
            binding.loginBtn.setEnabled(true)
            binding.loginBtn.setBackgroundColor(getColor(R.color.bg_btn_login_enabled))
            binding.loginBtn.setTextColor(getColor(R.color.login_text_enabled))
        } else {
            binding.loginBtn.setEnabled(false)
            binding.loginBtn.setBackgroundColor(getColor(R.color.bg_btn_login_disabled))
            binding.loginBtn.setTextColor(getColor(R.color.login_text_disabled))
        }
    }

    private fun loginValidation(): Boolean {
        return !(binding.loginId.length() == 0 || binding.loginPw.length() == 0)
    }

    private fun setStatusBarColor(color: Int){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, color));
    }
}