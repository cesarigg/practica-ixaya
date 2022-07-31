package com.ivangarcia.apiproducts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ivangarcia.apiproducts.R
import com.ivangarcia.apiproducts.adapter.CustomAdapter
import com.ivangarcia.apiproducts.adapter.OrderAdapter
import com.ivangarcia.apiproducts.model.OrderModel
import com.ivangarcia.apiproducts.model.ProductsViewModel
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersFragment : Fragment() {

    val orderList = ArrayList<OrderModel>()
    private lateinit var ordersRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        val title = view.findViewById<View>(R.id.Heading2) as TextView
        ordersRV = view.findViewById(R.id.recyclerview2)
        ordersRV.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)

        // Initializing Android Networking
        AndroidNetworking.initialize(requireActivity().applicationContext)

        // Actually making the GET request

        // Actually making the GET request
        AndroidNetworking.get("https://sandbox.ixaya.net/api/orders")
            .addHeaders("X-API-KEY", "kcok0cw44ww8888c40oo080o4ooo4swggwcs084k")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // Text will show success if Response is success

                    val respString = response.toString()
                    //title.setText(respString)
                    val result = JSONObject(respString)
                    val orders = result.getJSONArray("response")

                    for(i in 0 until orders.length()){
                        val order = orders.getJSONObject(i)
                        orderList.add(
                            OrderModel(order)
                        )
                    }

                    // This will pass the ArrayList to our Adapter
                    val adapter = OrderAdapter(orderList)

                    // Setting the Adapter with the recyclerview
                    ordersRV.adapter = adapter
                }

                override fun onError(anError: ANError) {
                    // Text will show failure if Response is failure
                    //textView.text = "Response Failure"
                }
            })



        // Inflate the layout for this fragment
        return view
    }
}