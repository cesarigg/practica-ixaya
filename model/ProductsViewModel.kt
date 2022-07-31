package com.ivangarcia.apiproducts.model

import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat

data class ProductsViewModel(val product: JSONObject){

    var formatter: NumberFormat = DecimalFormat("#,###")
    var myNumber = Integer.parseInt(product.get("price").toString())
    var price: String = formatter.format(myNumber)

    val id = product.get("id").toString()
    val category = product.get("category").toString()
    val title = product.get("title").toString()
    val short_description = product.get("short_description").toString()
    val description = product.get("description").toString()
    val sale_count = product.get("sale_count").toString()
    val discount = product.get("discount").toString()
    val pricenum = Integer.parseInt(product.get("price").toString())
    val enabled = product.get("enabled").toString()
    val image_url = product.get("image_url").toString()
    val create_date = product.get("create_date").toString()
    val last_update = product.get("last_update").toString()
}
