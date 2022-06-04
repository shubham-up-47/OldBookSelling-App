package com.example.oldbooksellingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val url = "http://192.168.1.100/OldBookSellingApp/get_category.php"
        var list = ArrayList<String>()
        var req:RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest( Request.Method.GET, url,null,

            {   response ->
                for(x in 0..response.length()-1){
                   list.add(response.getJSONObject(x).getString("category"))
                }

                var adp = ArrayAdapter(this, R.layout.my_textview, list)
                homeCategory.adapter = adp
            },

            {   error->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        req.add(jar)

        homeCategory.setOnItemClickListener { adapterView, view, position, id ->

            var categoryName:String = list[position]
            var intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("category", categoryName)
            startActivity(intent)
        }
    }
}