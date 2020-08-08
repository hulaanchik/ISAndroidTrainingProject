package com.example.isandroidtraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.isandroidtraining.model.Reqres
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    //Status between ViewModel and Component
    private val status = MutableLiveData<Resource<Reqres>>()

    fun getStatus(): LiveData<Resource<Reqres>> {
        return status
    }

    //Function for calling data
    fun fetchData(): Call<Reqres> {

        val call = DataAPI.invoke().getData()
        call.enqueue( object: Callback<Reqres> {
            //function when data calling is successful
            override fun onResponse(
                call: Call<Reqres>,
                response: Response<Reqres>
            ) {
                //setting Data list into status
                status.value = Resource.success(response.body())
            }
            //function when data calling is failed
            override fun onFailure(call: Call<Reqres>, t: Throwable) {
                //throwing Data list
                status.value = (Resource.error(t))
            }
        })
        return call

    }

    override fun onCleared() {
        super.onCleared()

    }
}

