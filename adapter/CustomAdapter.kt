package com.ivangarcia.apiproducts.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ivangarcia.apiproducts.MainActivity
import com.ivangarcia.apiproducts.R
import com.ivangarcia.apiproducts.model.CartItem
import com.ivangarcia.apiproducts.model.ProductsViewModel
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_design.view.*

class CustomAdapter(private val mList: List<ProductsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productsViewModel = mList[position]

        holder.bindProduct(mList[position])

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(productsViewModel.image)
        Picasso.get().load(mList[position].image_url).into(holder.itemView.imageview)

        // sets the text to the textview from our itemHolder class
        holder.itemView.title.text = productsViewModel.title
        holder.itemView.price.text = "$" + productsViewModel.price + " MXN"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindProduct(product: ProductsViewModel) {

            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

            itemView.addToCart.setOnClickListener { itemView ->

                val item = CartItem(product)

                ShoppingCart.addItem(item)
                //notify users
                Toast.makeText(itemView.context, "${product.title} se añadió a tu carrito", Toast.LENGTH_SHORT).show()


                it.onNext(ShoppingCart.getCart())

            }

            itemView.removeItem.setOnClickListener { itemView ->

                val item = CartItem(product)

                ShoppingCart.removeItem(item, itemView.context)

                Toast.makeText(itemView.context, "${product.title} se eliminó de tu carrito", Toast.LENGTH_SHORT).show()

                it.onNext(ShoppingCart.getCart())

            }
        }).subscribe { cart ->
                var quantity = 0

                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                }

                (itemView.context as MainActivity).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Carrito: $quantity Productos", Toast.LENGTH_SHORT).show()
            }
    }

}}
