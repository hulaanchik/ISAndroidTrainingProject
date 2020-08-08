package com.example.isandroidtraining

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isandroidtraining.model.Data
import com.example.isandroidtraining.model.Reqres
import com.example.isandroidtraining.user.LocalSharedPreferences
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recycler_view.recyclerView
import kotlinx.android.synthetic.main.fragment_search_view.*
import kotlin.collections.ArrayList


class SearchViewFragment : Fragment(){
    //In this Fragment we used the Recycler View with Search View

    private lateinit var viewModel: MyViewModel
    private lateinit var myAdapter: Adapter_SV

    val userData = MutableLiveData<Reqres>()
    val arrayList = ArrayList<Data>()
    val displayList = ArrayList<Data>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("success", "true")
        return inflater.inflate(R.layout.fragment_search_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Setting Search View widget
        searchRV.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        //The onQueryTextChange method called every time we typing on the SearchView and updates the RecyclerView with the new results
            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return  false
            }
        })
        displayList.addAll(arrayList)

        //In this fragment we additionally use search view, therefore we created new Adapter_SV
        myAdapter = Adapter_SV()
        recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = myAdapter
        }
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.fetchData()
        viewModel.getStatus().observe(viewLifecycleOwner, Observer {

            when { it.status == Resource.Status.SUCCESS -> {

                val data = it.data?.data
                val jsonDataList: String = Gson().toJson(it.data)
                val dataString = LocalSharedPreferences.save("Datalist", jsonDataList)
                myAdapter.setItems(data.orEmpty())
                Log.d("welcome", "welcome"+jsonDataList)

            }
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

