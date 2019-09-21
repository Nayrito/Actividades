package com.example.loginactivity.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginactivity.HomeFragment
import com.example.loginactivity.R
import com.example.loginactivity.SpotsFragment
import com.example.loginactivity.StoreFragment

private val TAB_TITLES = arrayOf(
    R.string.home,
    R.string.spots,
    R.string.store
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HomeFragment()
            1 -> return SpotsFragment()
            else -> return StoreFragment()

        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}