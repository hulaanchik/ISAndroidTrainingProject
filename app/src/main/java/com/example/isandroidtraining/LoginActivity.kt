package com.example.isandroidtraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.isandroidtraining.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    //Call view binding variable
    private lateinit var binding: ActivityLoginBinding

    lateinit var mLogin: Button
    lateinit var mUser: EditText
    lateinit var mPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting layout file in activity through binding
        binding = ActivityLoginBinding.inflate(layoutInflater)

        //setting layout pallettes in variables
        mLogin = binding.btnLogin
        mUser = binding.etUser
        mPass = binding.etPass

        //the action of clicking Login button
        mLogin.setOnClickListener{

            Log.d("hoho", binding.etUser.toString())
            if (binding.etUser.text.toString() == "is"
                && binding.etPass.text.toString() =="0"
            )
            {
                //Log is used for checking whether the given command is working or not
                Log.d("HERE", "THERE")
                val intent= Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else { print("Incorrect username or password")}
        }
    setContentView(binding.root)
    }
}