package com.example.oldbooksellingapp

import android.content.Intent
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationButton.setOnClickListener{

            if(registrationPassword1.text.toString().equals(registrationPassword2.text.toString()))
            {
                var url = "http://10.2.90.3/OldBookSellingApp/add_user.php?mobile=" + registrationMobile.text.toString() +
                "&password=" + registrationPassword1.text.toString() +
                "&name=" + registrationName.text.toString() + "&address=" + registrationAddress.text.toString()

                var req:RequestQueue = Volley.newRequestQueue(this)
                var reqStr = StringRequest( Request.Method.GET, url,

                    {   response->
                        if(response.equals("0")){
                            Toast.makeText(this, "Mobile already used", Toast.LENGTH_LONG).show()
                        }
                        else{
                            UserInfo.mobile = registrationMobile.text.toString()
                            Toast.makeText(this, "New user created", Toast.LENGTH_LONG).show()
                            val i = Intent(this, HomeActivity::class.java)
                            startActivity(i)
                        }
                    },

                    {   error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                    }
                )

                req.add(reqStr)
            }
            else
            {
                Toast.makeText(this, "Password not matched", Toast.LENGTH_LONG).show()
            }
        }
    }
}