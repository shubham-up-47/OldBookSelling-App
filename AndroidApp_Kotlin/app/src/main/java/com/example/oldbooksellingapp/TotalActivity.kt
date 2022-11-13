package com.example.oldbooksellingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_total.*
import java.math.BigDecimal

class TotalActivity : AppCompatActivity()
{
    var amount:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        var bno: String? = intent.getStringExtra("bno")

        var url = "http://10.2.90.3/OldBookSellingApp/get_total.php?bill_no=" + bno

        var req: RequestQueue = Volley.newRequestQueue(this)
        var reqStr = StringRequest(Request.Method.GET, url,

            { response ->
                totalTV.text = "Total Price: $ " + response
                amount = response.toDouble()
            },

            { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        req.add(reqStr)

        paypalButton.setOnClickListener {
            Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()

            val i = Intent(this, ConfirmActivity::class.java)
            startActivity(i)
        }
/*
        paypalButton.setOnClickListener {



        }
    }


        val ClientId  = "AV978oFw07V-8yYeGz0JgCYErKpumHNLwzVrVf46ywrSN9nP2Qf3kwyvnJBzrfV-wLvtcZAosSE5W0VJ"
        val config = CheckoutConfig(
            application,
            clientId = ClientId,
            environment = com.paypal.checkout.config.Environment.SANDBOX,
            returnUrl = "BuildConfig.APPLICATION_ID",
            currencyCode = CurrencyCode.USD,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)

        var i = Intent(this, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)

        paypalButton.setOnClickListener {

            var payment = PayPalPayment(BigDecimal.valueof(amount), "USD", "OldBookSellingApp", PayPalPayments.PAYMENT_INTENT_SALE)

            var intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
            startActivityForResult(, "123")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==123){
            if(resultCode==Activity.RESULT_OK){
                var obj = Intent(this, ConfirmActivity::class.java)
                startActivity(obj)
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

    */
    }
}