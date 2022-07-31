package com.ivangarcia.apiproducts.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ivangarcia.apiproducts.fragments.HomeFragment
import com.ivangarcia.apiproducts.fragments.OrdersFragment

internal class NavAdapter(fm:FragmentManager?):
    FragmentPagerAdapter(fm!!)
    {
        override fun getItem(position: Int) : Fragment {
            return when(position) {

                0 -> {
                    HomeFragment()
                }
                1 -> {
                    OrdersFragment()
                }
                else -> HomeFragment()
            }
        }
        override fun getCount(): Int {

            return 2
        }
    }
