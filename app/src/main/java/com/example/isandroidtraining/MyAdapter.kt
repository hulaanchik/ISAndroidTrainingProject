package com.example.isandroidtraining

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.isandroidtraining.model.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_item.view.*


class MyAdapter : RecyclerView.Adapter<MyHolder>() {
    private var dataList = ArrayList<Data>()
    private lateinit var context: Context


    //Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context;
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false))
    }

    //function for setting items of list
    fun setItems(list: List<Data>) {
        dataList = ArrayList(list)
        notifyDataSetChanged()
    }

    //function for adding items of list
    fun addItems(list: List<Data>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    //Gets the number of items in the list
    override fun getItemCount() = dataList.size

    //Bind each item in the List to a view
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]

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

}
