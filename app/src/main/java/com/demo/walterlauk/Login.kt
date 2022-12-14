package com.demo.walterlauk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demo.walterlauk.databinding.ActivityLoginBinding
import com.demo.walterlauk.network.ApiInterface
import com.demo.walterlauk.network.Responce
import com.demo.walterlauk.repo.LoginRepo
import com.demo.walterlauk.viewModel.LoginViewModel
import com.demo.walterlauk.viewModelFactory.LoginViewModelFactory

class Login : AppCompatActivity() {

    private lateinit var mBinding : ActivityLoginBinding
    private lateinit var mRepo : LoginRepo
    private lateinit var mViewModel : LoginViewModel
    private lateinit var mApi : ApiInterface
    private var userName : String = ""
    private var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mApi = ApiInterface.create()
        mRepo = LoginRepo(mApi , this )
        mViewModel = ViewModelProvider(this , LoginViewModelFactory(mRepo)).get(LoginViewModel::class.java)

        mBinding.loginBtn.setOnClickListener {
            checkUserDetails()
        }


    }

    private fun checkUserDetails() {
        userName = mBinding.userNameEt.text.toString()
        password = mBinding.passwordEt.text.toString()

        if (userName.isEmpty()){
            Toast.makeText(this , "Please Enter Username..." , Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty()){
            Toast.makeText(this , "Please Enter Password..." , Toast.LENGTH_SHORT).show()
            return
        }

        mViewModel.getUserLogin(userName , password)
        observeUserLogin()

    }

    private fun observeUserLogin() {
        mViewModel.userLogin.observe(this){
            when (it) {
                is Responce.Loading -> {
                }
                is Responce.Success -> {
                    if (it.data?.message== "Login successfully."){
                        val intent = Intent(this , DeaprtureControl::class.java)
                        startActivity(intent)
                        Log.e("kjfdkj" , it.toString() )
                    }else{
                        Toast.makeText(this , "Please enter correct Gmail or Password" , Toast.LENGTH_SHORT).show()
                        return@observe
                    }
                }
                is Responce.NetworkError<*> -> {
                }
                is Responce.Error -> {
                }
            }

        }
    }
}