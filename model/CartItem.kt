package com.ivangarcia.apiproducts.model

import org.json.JSONArray

data class CartItem(var product: ProductsViewModel, var quantity: Int = 0){
    var street: String = String()
    var address: String = String()
    var city: String = String()
    var state: String = String()
    var zipcode: String = String()
    var phone: String = String()
    var productsList: JSONArray = JSONArray()

}