package com.ivangarcia.apiproducts

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ivangarcia.apiproducts.adapter.NavAdapter
import com.ivangarcia.apiproducts.adapter.ShoppingCart
import com.ivangarcia.apiproducts.screens.ConfirmCheckout
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {


    private lateinit var mViewPager: ViewPager
    private lateinit var homeBtn: ImageButton
    private lateinit var ordersBtn: ImageButton
    private lateinit var shoppingBtn: ImageButton
    private lateinit var mPagerAdapter: NavAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)
        Paper.clear(this)

        mViewPager = findViewById(R.id.mViewPager)
        homeBtn = findViewById(R.id.homeBtn)
        ordersBtn = findViewById(R.id.ordersBtn)
        shoppingBtn = findViewById(R.id.shoppingBtn)

        mPagerAdapter = NavAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 1


        // add page change listener

        mViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        // default Tab
        mViewPager.currentItem = 0
        homeBtn.setImageResource(R.drawable.ic__home_purple)

        homeBtn.setOnClickListener(){
            mViewPager.currentItem = 0
        }

        ordersBtn.setOnClickListener(){
            mViewPager.currentItem = 1
        }

        shoppingBtn.setOnClickListener(){
            var totalPrice = ShoppingCart.getCart()
                .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.pricenum!!.toDouble()) }
            if(ShoppingCart.getShoppingCartSize() > 0) {
                val intent = Intent(this, ConfirmCheckout::class.java).apply {
                    putExtra("EXTRA_MESSAGE", totalPrice.toString())
                }

                startActivity(intent)
            }
        }

    }

    fun changingTabs(position: Int) {
        if(position == 0){
            homeBtn.setImageResource(R.drawable.ic__home_purple)
            ordersBtn.setImageResource(R.drawable.ic_receipt_black)
            shoppingBtn.setImageResource(R.drawable.ic_shopping_black)
        }
        if(position == 1){
            homeBtn.setImageResource(R.drawable.ic_home_black)
            ordersBtn.setImageResource(R.drawable.ic_receipt_purple)
            shoppingBtn.setImageResource(R.drawable.ic_shopping_black)
        }
    }
}