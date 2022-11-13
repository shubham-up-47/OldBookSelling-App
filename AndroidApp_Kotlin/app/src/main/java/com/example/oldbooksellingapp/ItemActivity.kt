package com.example.oldbooksellingapp

import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        var categoryName:String? = intent.getStringExtra("category")
        var url = "http://10.2.90.3/OldBookSellingApp/get_items.php?category=" + categoryName
        var list = ArrayList<Item>()

        var req:RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest( Request.Method.GET, url, null,

            {   response ->
                for (x in 0..response.length() - 1) {
                    list.add(
                        Item(
                            response.getJSONObject(x).getInt("id"),
                            response.getJSONObject(x).getString("name"),
                            response.getJSONObject(x).getDouble("price"),
                            response.getJSONObject(x).getString("photo")
                        )
                    )
                }

                var adp = ItemAdapter(this, list)
                itemRV.layoutManager = LinearLayoutManager(this)
                itemRV.adapter = adp
            },

            {   error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        req.add(jar)
    }
}