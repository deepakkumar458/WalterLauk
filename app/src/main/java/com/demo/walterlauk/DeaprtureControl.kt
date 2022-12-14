package com.demo.walterlauk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demo.walterlauk.adapter.TrailerPartsAdapter
import com.demo.walterlauk.adapter.TruckPartsAdapter
import com.demo.walterlauk.databinding.ActivityDeaprtureControlBinding
import com.demo.walterlauk.model.TrailerParts
import com.demo.walterlauk.model.TruckParts
import com.demo.walterlauk.network.ApiInterface
import com.demo.walterlauk.network.Responce
import com.demo.walterlauk.repo.LoginRepo
import com.demo.walterlauk.viewModel.LoginViewModel
import com.demo.walterlauk.viewModelFactory.LoginViewModelFactory

class DeaprtureControl : AppCompatActivity() {

    private lateinit var mBinding : ActivityDeaprtureControlBinding
    private lateinit var mRepo : LoginRepo
    private lateinit var mViewModel : LoginViewModel
    private lateinit var mApi : ApiInterface
    private lateinit var mTruckAdapter : TruckPartsAdapter
    private lateinit var mTrailerAdapter : TrailerPartsAdapter
    private lateinit var mTruckList : List<TruckParts>
    private lateinit var mTrailerList : List<TrailerParts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDeaprtureControlBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mApi = ApiInterface.create()
        mRepo = LoginRepo(mApi , this )
        mViewModel = ViewModelProvider(this , LoginViewModelFactory(mRepo)).get(LoginViewModel::class.java)

        mViewModel.getParts("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3dsY3NhcHAuZGUvd2FsdGVyX2xhdWsvYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE2NzAzMjQ3ODgsImV4cCI6MTY3MDkyOTU4OCwibmJmIjoxNjcwMzI0Nzg4LCJqdGkiOiJBeUNkZERoMUlYZ3Rzc1Q5Iiwic3ViIjoiMTEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.Zb72ajjUzEQjAFjh_SfZ_G30KhGTshGTz1y2NqfKQNY")
        observeParts()
    }

    private fun observeParts() {
        mViewModel.parts.observe(this) {
            when (it) {
                is Responce.Loading -> {
                }
                is Responce.Success -> {
                    mTruckList = it.data?.data!!.Truck
                    mTruckAdapter = TruckPartsAdapter(mTruckList, this)
                    mBinding.truckRv.adapter = mTruckAdapter


                    mTrailerList = it.data!!.data!!.Trailer
                    mTrailerAdapter = TrailerPartsAdapter(mTrailerList, this)
                    mBinding.trailerRv.adapter = mTrailerAdapter
                }
                is Responce.NetworkError<*> -> {
                }
                is Responce.Error -> {
                }
            }
        }
    }
}