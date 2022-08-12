package com.example.testapp10

class RequestModel {
    private val date: String? = null
    private val bank: String? = null
    private val baseCurrency: Int? = null
    private val baseCurrencyLit: String? = null
    val exchangeRate: List<ExchangeRate>? = null

    inner class ExchangeRate {
        val baseCurrency: String? = null
        val currency: String? = null
        val saleRateNB: Double? = null
        val purchaseRateNB: Double? = null
        val saleRate: Double? = null
        val purchaseRate: Double? = null
    }
}