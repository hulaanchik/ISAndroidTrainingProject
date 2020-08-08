package com.example.isandroidtraining

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.isandroidtraining.model.Data
import com.example.isandroidtraining.model.Reqres
import com.example.isandroidtraining.user.LocalSharedPreferences
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import java.util.List.of

class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: MyViewModel
    private lateinit var myAdapter: MyAdapter

    val userData = MutableLiveData<Reqres>()

    //Inflating a Recycler View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("success", "true")
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //getting Adapter file
        myAdapter = MyAdapter()
        recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = myAdapter
        }
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        //Calling data
        viewModel.fetchData()
        //getting status, which means data
        viewModel.getStatus().observe(viewLifecycleOwner, Observer {
            //when status is successful saving data in Shared Preference and setting it to MyAdapter
            when { it.status == Resource.Status.SUCCESS -> {

                val data = it.data?.data
                val jsonDataList: String = Gson().toJson(it.data)
                val dataString = LocalSharedPreferences.save("Datalist", jsonDataList)
                myAdapter.setItems(data.orEmpty())
                Log.d("welcome", "welcome"+jsonDataList)

            }
                //when status is failed getting saved data from Shared Preference and  setting it to MyAdapter
                it.status == Resource.Status.ERROR -> {

                    it.exception!!.let{
                        val json = LocalSharedPreferences.getValueString("Datalist")
                        val GsonDataList: Reqres = Gson().fromJson(json, Reqres::class.java)
                        Log.d("dada", "dada"+GsonDataList)
                        myAdapter.setItems(GsonDataList.data)

                    }
                }
            }
        })
    }
}