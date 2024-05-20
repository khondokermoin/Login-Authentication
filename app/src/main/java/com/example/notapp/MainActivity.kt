package com.example.notapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notapp.databinding.ActivityMainBinding
import com.example.notapp.viewmodels.AuthViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:AuthViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.registerBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPass = binding.conPassEt.text.toString()
            if(email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this,"Please fill up all the fields!!!", Toast.LENGTH_SHORT).show()
            }else if(!password.equals(confirmPass)){
                Toast.makeText(this,"Password doesn't matched!!!", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.signup(email, confirmPass).observe(this, {result->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    if(it.equals("Sign in successful")){
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                })

            }
        }
        binding.loginTxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}