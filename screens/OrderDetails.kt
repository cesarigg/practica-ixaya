package com.ivangarcia.apiproducts.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ivangarcia.apiproducts.R
import kotlinx.android.synthetic.main.activity_order_details.*
import org.json.JSONObject

class OrderDetails : AppCompatActivity() {

    lateinit var detailId: TextView
    lateinit var detailUserId: TextView
    lateinit var detailPhone:TextView
    lateinit var detailStreet : TextView
    lateinit var detailAddress:TextView
    lateinit var detailCity: TextView
    lateinit var detailState:TextView
    lateinit var detailZipCode:TextView
    lateinit var detailDiscount:TextView
    lateinit var detailSubtotal:TextView
    lateinit var detailTotal:TextView
    lateinit var detailOrderCode:TextView
    lateinit var detailEnabled:TextView
    lateinit var detailCreateDate:TextView
    lateinit var detailLastUpdate:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val extraMsj = intent.getStringExtra("EXTRA_MESSAGE")
        val jsonOrder = JSONObject(extraMsj)

        detailId = findViewById(R.id.detailId)
        detailUserId = findViewById(R.id.detailUserId)
        detailPhone = findViewById(R.id.detailPhone)
        detailAddress = findViewById(R.id.detailAddress)
        detailStreet =  findViewById(R.id.detailStreet)
        detailCity = findViewById(R.id.detailCity)
        detailState = findViewById(R.id.detailState)
        detailZipCode = findViewById(R.id.detailZipCode)
        detailDiscount = findViewById(R.id.detailDiscount)
        detailSubtotal = findViewById(R.id.detailSubtotal)
        detailTotal = findViewById(R.id.detailTotal)
        detailOrderCode = findViewById(R.id.detailOrderCode)
        detailEnabled = findViewById(R.id.detailEnabled)
        detailCreateDate = findViewById(R.id.detailCreateDate)
        detailLastUpdate = findViewById(R.id.detailLastUpdate)

        detailId.setText("ID de la compra: " + jsonOrder.get("id").toString())
        detailUserId.setText("ID de usuario: " + jsonOrder.get("user_id").toString())
        detailPhone.setText("Teléfono: " + jsonOrder.get("phone").toString())
        detailAddress.setText("Colonia: " + jsonOrder.get("address").toString())
        detailCity.setText("Ciudad: " + jsonOrder.get("city").toString())
        detailState.setText("Estado: " + jsonOrder.get("state").toString())
        detailStreet.setText("Calle: " + jsonOrder.get("street_name").toString())
        detailZipCode.setText("Código Postal: " + jsonOrder.get("zip_code").toString())
        detailDiscount.setText("Descuento: $" + jsonOrder.get("discount").toString())
        detailSubtotal.setText("Subtotal: $" + jsonOrder.get("subtotal").toString())
        detailTotal.setText("Subtotal: $" + jsonOrder.get("total").toString())
        detailOrderCode.setText("Código de envío: " + jsonOrder.get("order_code").toString())
        detailEnabled.setText("Habilitado: " + jsonOrder.get("enabled").toString())
        detailCreateDate.setText("Fecha de creación: " + jsonOrder.get("create_date").toString())
        detailLastUpdate.setText("Última actualización: " + jsonOrder.get("last_update").toString())


    }
}