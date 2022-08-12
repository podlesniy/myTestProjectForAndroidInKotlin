package com.example.testapp10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.testapp10.MainActivity.Companion.exchangeRate
import com.example.testapp10.RequestModel.ExchangeRate
import java.util.*


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        viewFullInfo(exchangeRate)
    }

    private fun viewFullInfo(country: ExchangeRate) {
        val baseCurrency: TextView = findViewById(R.id.baseCurrency)
        val currency: TextView = findViewById(R.id.currency)
        val saleRateNB: TextView = findViewById(R.id.saleRateNB)
        val purchaseRateNB: TextView = findViewById(R.id.purchaseRateNB)
        val saleRate: TextView = findViewById(R.id.saleRate)
        val purchaseRate: TextView = findViewById(R.id.purchaseRate)
        baseCurrency.text = country.baseCurrency
        currency.text = country.currency
        saleRateNB.text = String.format(Locale.US, "%s", country.saleRateNB)
        purchaseRateNB.text = String.format(Locale.US, "%s", country.purchaseRateNB)
        saleRate.text = String.format(Locale.US, "%s", country.saleRate)
        purchaseRate.text = String.format(Locale.US, "%s", country.purchaseRate)
    }

}