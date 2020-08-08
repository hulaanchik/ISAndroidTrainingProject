package com.example.isandroidtraining

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MyHolder (v: View) : RecyclerView.ViewHolder(v) {
    //Holds the items that will add eat item to
    val userFirstNameTextView = v.first_name
    val userLastNameTextView = v.last_name
    val avatarImgView = v.avatar
    val emailTextView = v.email
}
