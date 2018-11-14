package com.tinh.dev.poly.contentprovicekotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.tinh.dev.poly.contentprovicekotlin.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity() : AppCompatActivity() {
      var adaptera: Adapter? = null
      var list: ArrayList<ImageModel>? = null
     var layoutManager: LinearLayoutManager? = null
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isPermissionGranted()
        fab.setOnClickListener(View.OnClickListener {
            imageProvice()
        })
        layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        list= arrayListOf()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun isPermissionGranted():Boolean{
        if (Build.VERSION.SDK_INT >= 23) {
            var array:Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this,array, 2);
                return false;
            }
        }
        else {
            Log.v("TAG","Permission is granted");
            return true;
        }
    }





     fun imageProvice(){
        var  uri:Uri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var abc:Array<String> = arrayOf(
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns._ID,
                MediaStore.Images.Media.DATA)
        var cursor:Cursor=contentResolver.query(uri,abc,null,null,null)

        if (cursor.count>0){
            cursor.moveToFirst()
            list?.clear()
            while (cursor.isAfterLast==false){
                var name:String=cursor.getString(0)
                var id :String=cursor.getString(1)
                var uriimg:String=cursor.getString(2)
                var img:ImageModel= ImageModel()
                img.name=name
                img.id=id
                img.uri=uriimg
                 list?.add(img)
                cursor.moveToNext()
            }
            cursor.close()
            adaptera= Adapter(list!!,this)
            var linearLayoutManager:LinearLayoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager=linearLayoutManager
            recyclerview.adapter=adaptera

        }
    }

}


