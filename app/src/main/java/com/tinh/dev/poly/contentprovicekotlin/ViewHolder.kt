package com.tinh.dev.poly.contentprovicekotlin

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var name:TextView= itemView!!.findViewById(R.id.imgname)
    var id:TextView= itemView!!.findViewById(R.id.imgid)
    var img:ImageView= itemView!!.findViewById(R.id.img)

}