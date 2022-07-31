package com.ivangarcia.apiproducts.model

import android.widget.EditText
import android.widget.TextView
import com.ivangarcia.apiproducts.adapter.ShoppingCartAdapter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import com.ivangarcia.apiproducts.adapter.ShoppingCart
import org.json.JSONObject
import java.util.stream.Collectors.toMap

data class OrderModel(val order: JSONObject) {
    var jsonObj = order
    var id: String = order.get("id").toString()
    var user_id: String = order.get("user_id").toString()
    var phone: String = order.get("phone").toString()
    var address: String = order.get("address").toString()
    var city: String = order.get("city").toString()
    var state: String = order.get("state").toString()
    var street_name: String = order.get("street_name").toString()
    var zip_code: String = order.get("zip_code").toString()
    var discount: String = order.get("discount").toString()
    var subtotal: String = order.get("subtotal").toString()
    var total: String = order.get("total").toString()
    var order_code: String = order.get("order_code").toString()
    var paid: String = order.get("paid").toString()
    var enabled: String = order.get("enabled").toString()
    var create_date: String = order.get("create_date").toString()
    var last_update: String = order.get("last_update").toString()
}