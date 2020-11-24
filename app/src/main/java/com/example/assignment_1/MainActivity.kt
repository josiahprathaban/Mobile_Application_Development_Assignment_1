package com.example.assignment_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.assignment_1.posts.api.PostApi
import com.example.assignment_1.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val postApi= retrofit.create(PostApi::class.java)
        val postCall= postApi.posts;

        postCall.enqueue(object  : Callback<List<Post>> {

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("Failure", t.toString())
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val titleList: List<String> = response.body()!!.map { it.title }
                createAndShowAdapter(titleList.toTypedArray())
            }

        })
    }

    fun createAndShowAdapter (titleList: Array<String>){
        var listView = findViewById<ListView>(R.id.titlelist)
        val arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, titleList)
        listView.adapter = arrayAdapter
    }
}