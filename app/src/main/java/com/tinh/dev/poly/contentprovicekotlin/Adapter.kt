package com.tinh.dev.poly.contentprovicekotlin

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class Adapter(var list: ArrayList<ImageModel>,var context:Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout:LayoutInflater= LayoutInflater.from(parent.context)
        var view:View=layout.inflate(R.layout.cardview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         Log.e("Tinh",list.size.toString())
         holder.name.setText(list.get(position).name)
         holder.id.setText(list.get(position).id)
         holder.img.setImageURI(Uri.parse(list.get(position).uri))

    }
}