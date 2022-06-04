package com.example.oldbooksellingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signupButton.setOnClickListener {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }

        loginButton.setOnClickListener{

            var url = "http://192.168.1.100/OldBookSellingApp/login.php?mobile=" + loginMobile.text.toString() +
                    "&password=" + loginPassword.text.toString()

            var req: RequestQueue = Volley.newRequestQueue(this)
            var reqStr = StringRequest( Request.Method.GET, url,

                {   response->
                    if(response.equals("0")){
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                    else {
                        UserInfo.mobile = loginMobile.text.toString()
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

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
    }
}