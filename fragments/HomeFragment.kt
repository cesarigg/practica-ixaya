package com.ivangarcia.apiproducts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ivangarcia.apiproducts.R
import com.ivangarcia.apiproducts.adapter.CustomAdapter
import com.ivangarcia.apiproducts.adapter.ShoppingCart
import com.ivangarcia.apiproducts.model.ProductsViewModel
import com.ivangarcia.apiproducts.model.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val productList = ArrayList<ProductsViewModel>()
    private lateinit var productsRV: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        productsRV = view.findViewById(R.id.recyclerview)
        productsRV.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)

        // Defining TextView which we made in XML
        // Defining TextView which we made in XML
        //val textView = findViewById<TextView>(R.id.text)


        // Initializing Android Networking
        AndroidNetworking.initialize(requireActivity().applicationContext)

        // Actually making the GET request
        AndroidNetworking.get("https://sandbox.ixaya.net/api/products")
            .addHeaders("X-API-KEY", "kcok0cw44ww8888c40oo080o4ooo4swggwcs084k")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // Text will show success if Response is success

                    val respString = response.toString()
                    val result = JSONObject(respString)
                    val products = result.getJSONArray("response")
                    //val aux = products.getJSONObject(3)

                    for(i in 0 until products.length()){
                        val product = products.getJSONObject(i)
                        productList.add(
                            ProductsViewModel(product)
                        )
                    }

                    // This will pass the ArrayList to our Adapter
                    val adapter = CustomAdapter(productList)

                    // Setting the Adapter with the recyclerview
                    productsRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError) {
                    // Text will show failure if Response is failure
                    //textView.text = "Response Failure"
                }
            })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}