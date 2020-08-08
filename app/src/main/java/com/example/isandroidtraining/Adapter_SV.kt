package com.example.isandroidtraining

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.isandroidtraining.model.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search_view.view.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class Adapter_SV : RecyclerView.Adapter<MyHolder>(), Filterable {
    private var dataList = ArrayList<Data>()
    //In this adapter we added new var, which will be displayed after search
    private var displayList = ArrayList<Data>()
    private lateinit var context: Context

    init {
        displayList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context;

        return MyHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false))
    }

    fun setItems(list: List<Data>) {
        dataList = ArrayList(list)
        notifyDataSetChanged()

    }

    fun addItems(list: List<Data>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }



    override fun getItemCount() : Int { return displayList.size }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val data = displayList[position]

        val userFirstNameTextView = holder.itemView.first_name
        val userLastNameTextView = holder.itemView.last_name
        val avatarImgView = holder.itemView.avatar
        val emailTextView = holder.itemView.email

        val firstName = data.firstName
        userFirstNameTextView.text = firstName

        val lastName = data.lastName
        userLastNameTextView.text = lastName

        Picasso.get()
            .load(data.avatar)
            .into(avatarImgView)

        val Email = data.email
        emailTextView.text = Email

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            //Checks if we have typed a text in the SeachView
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                //If there is not any text, will return all items
                if (charSearch.isEmpty()){
                    displayList = dataList
                }
                //If there is a text, then check if the characters match the items from the list and return the results in a FilterResults type
                else {
                    val resultList = ArrayList<Data>()
                    for (row in dataList) {
                        if (row.firstName.contains(charSearch.toString())) {
                            resultList.add(row)
                        }
                    }
                    displayList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = displayList
                return filterResults
            }
            //Get results and passes it to displayList and updates recycler view
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                displayList = results?.values as ArrayList<Data>
                notifyDataSetChanged()
            }
        }
    }
}
