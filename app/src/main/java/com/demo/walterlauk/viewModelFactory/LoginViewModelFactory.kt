package com.demo.walterlauk.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.walterlauk.repo.LoginRepo
import com.demo.walterlauk.viewModel.LoginViewModel

class LoginViewModelFactory (private val mRepo: LoginRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(mRepo) as T
    }
}