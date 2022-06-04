package com.example.oldbooksellingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var url = "http://192.168.1.100/OldBookSellingApp/get_tempOrder.php?mobile=" + UserInfo.mobile

        var list = ArrayList<String>()
        var req: RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest( Request.Method.GET, url, null,

            {   response ->
                for(x in 0..response.length()-1){
                    list.add("Name: " + response.getJSONObject(x).getString("name") + "\n"
                            + "Price: $ " + response.getJSONObject(x).getString("price") + "\n"
                            + "Qty: " + response.getJSONObject(x).getString("qty"))
                }

                var adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
                orderLV.adapter = adp
            },

            {   error->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        req.add(jar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.itemMenu){
            var i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        if(item.itemId==R.id.itemCancel){
            var url = "http://192.168.1.100/OldBookSellingApp/cancel_tempOrder.php?mobile=" + UserInfo.mobile

            var req:RequestQueue = Volley.newRequestQueue(this)
            var reqStr = StringRequest( Request.Method.GET, url,

                {   response ->
                    var i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                },

                {   error->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }
            )

            req.add(reqStr)
        }

        if(item.itemId==R.id.itemConfirm){
            var url = "http://192.168.1.100/OldBookSellingApp/confirm_tempOrder.php?mobile=" + UserInfo.mobile

            var req:RequestQueue = Volley.newRequestQueue(this)
            var reqStr = StringRequest( Request.Method.GET, url,

                {   response ->
                    var i = Intent(this, TotalActivity::class.java)
                    i.putExtra("bno",response)
                    startActivity(i)
                },

                {   error->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }
            )

            req.add(reqStr)
        }

        return super.onOptionsItemSelected(item)
    }

}



