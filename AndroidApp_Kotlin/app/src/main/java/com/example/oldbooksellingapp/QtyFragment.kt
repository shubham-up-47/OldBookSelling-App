package com.example.oldbooksellingapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class QtyFragment : android.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        var v: View = inflater.inflate(R.layout.fragment_qty, container, false)

        var et = v.findViewById<EditText>(R.id.etQty)      // et = EditText widget
        var btn = v.findViewById<Button>(R.id.btnQty)      // btn = Button widget

        btn.setOnClickListener {
            var url = "http://192.168.1.100/OldBookSellingApp/add_tempOrder.php?mobile=" + UserInfo.mobile + "&itemid=" + UserInfo.itemId + "&qty=" + et.text.toString()
            var req:RequestQueue = Volley.newRequestQueue(activity)
            var reqStr = StringRequest( Request.Method.GET, url,

                {   response ->
                    var i:Intent = Intent(activity, OrderActivity::class.java)
                    startActivity(i)
                },

                {   error->
                    Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                }
            )

            req.add(reqStr)
        }

        return v
    }

}   // Required empty public constructor







