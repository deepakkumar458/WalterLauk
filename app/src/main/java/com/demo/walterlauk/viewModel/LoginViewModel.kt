package com.demo.walterlauk.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.walterlauk.model.Login
import com.demo.walterlauk.model.Parts
import com.demo.walterlauk.network.Responce
import com.demo.walterlauk.repo.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(val mRepo : LoginRepo):ViewModel() {

    val userLogin : MutableLiveData<Responce<Login>>
    get() = mRepo.mUserLogin

     fun getUserLogin(user_name : String , password : String){
        viewModelScope.launch {
            mRepo.getUserLogin(user_name, password)
        }
    }


    val parts : MutableLiveData<Responce<Parts>>
    get() = mRepo.mParts

    fun getParts(token : String){
        viewModelScope.launch {
            mRepo.getParts(token)
        }
    }

}