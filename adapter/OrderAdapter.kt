package com.ivangarcia.apiproducts.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ivangarcia.apiproducts.MainActivity
import com.ivangarcia.apiproducts.R
import com.ivangarcia.apiproducts.model.OrderModel
import com.ivangarcia.apiproducts.screens.ConfirmCheckout
import com.ivangarcia.apiproducts.screens.OrderDetails
import kotlinx.android.synthetic.main.order_element.view.*
import org.json.JSONObject


class OrderAdapter(private val orderList: List<OrderModel>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_element,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: OrderAdapter.ViewHolder, position: Int) {
        viewHolder.bindItem(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    class ViewHolder(orderView: View) : RecyclerView.ViewHolder(orderView){
        fun bindItem(orderModel: OrderModel){

            itemView.orderTotal.setText("Total: $" + orderModel.total)
            itemView.orderId.setText("ID: "+ orderModel.id)
            itemView.cityState.setText("A: "  + orderModel.city + ", " + orderModel.state)
            itemView.code.setText("Código del pedido" + orderModel.order_code)
            itemView.date.setText("Fecha: " + orderModel.create_date)

            itemView.detailsBtn.setOnClickListener(){
                val intent = Intent(itemView.context, OrderDetails::class.java).apply {
                    putExtra("EXTRA_MESSAGE", orderModel.jsonObj.toString())
                }
                startActivity(itemView.context,intent,null)
        }
    }
    }
}
