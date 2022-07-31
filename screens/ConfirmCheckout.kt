package com.ivangarcia.apiproducts.screens

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ivangarcia.apiproducts.R
import com.ivangarcia.apiproducts.adapter.ShoppingCart
import com.ivangarcia.apiproducts.adapter.ShoppingCartAdapter
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat


class ConfirmCheckout : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter
    lateinit var totalprice: TextView
    lateinit var street: EditText
    lateinit var address: EditText
    lateinit var city: EditText
    lateinit var state: EditText
    lateinit var zipcode: EditText
    lateinit var phone: EditText
    lateinit var shopping_cart_recyclerView: RecyclerView
    lateinit var sendBtn: Button
    var jsonObj: JSONObject = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_checkout)

        val getprice = intent.getStringExtra("EXTRA_MESSAGE")
        totalprice = findViewById(R.id.totalprice)
        street = findViewById(R.id.street)
        address = findViewById(R.id.address)
        city = findViewById(R.id.city)
        state = findViewById(R.id.state)
        city = findViewById(R.id.city)
        state = findViewById(R.id.state)
        zipcode = findViewById(R.id.zipcode)
        phone = findViewById(R.id.phone)
        sendBtn = findViewById(R.id.sendBtn)

        var totalPrice = ShoppingCart.getCart()
            .fold(0) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.pricenum!!) }
        var formatter: NumberFormat = DecimalFormat("#,###")
        var myNumber = Integer.parseInt(totalPrice.toString())
        var finalprice: String = formatter.format(myNumber)

        totalprice.text = "$" + finalprice.toString()

        shopping_cart_recyclerView = findViewById(R.id.recyclerview3)
        adapter = ShoppingCartAdapter(ShoppingCart.getCart())
        shopping_cart_recyclerView.adapter = adapter
        shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)

        sendBtn.setOnClickListener(){
            if(street.text.toString() != "" && address.text.toString() != "" && city.text.toString() != "" && state.text.toString() != "" && zipcode.text.toString() != "" && phone.text.toString() != ""){

                val productsList = ShoppingCart.getOrderList()
                jsonObj.put("street_name", street.text.toString())
                jsonObj.put("adress,", address.text.toString())
                jsonObj.put("city", city.text.toString())
                jsonObj.put("state", state.text.toString())
                jsonObj.put("zip_code", zipcode.text.toString())
                jsonObj.put("phone", phone.text.toString())
                jsonObj.put("product_list", productsList)
                onCheckout(jsonObj, this)
                Toast.makeText(this, "Tu solicitud está siendo procesada : ${jsonObj.toString()}", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Tus datos no están completos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun onCheckout(_jsonObj: JSONObject, _context: Context){
        Toast.makeText(this, "El botón si funciona", Toast.LENGTH_SHORT).show()
        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.enableLogging();
        AndroidNetworking.post("https://sandbox.ixaya.net/api/orders/create")
            .addHeaders("X-API-KEY", "kcok0cw44ww8888c40oo080o4ooo4swggwcs084k")
            .setContentType("application/json")
            .addJSONObjectBody(_jsonObj)
            .setTag("test")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // Text will show success if Response is success
                    val msj = response.toString()


                    Toast.makeText(_context, "${msj}: Tu compra fue exitosa", Toast.LENGTH_SHORT).show()

                }

                override fun onError(anError: ANError) {
                    // Text will show failure if Response is failure
                    Toast.makeText(_context, "Error ${anError.errorCode}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
