package com.scodeid.mobilecomputingcollage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.scodeid.mobilecomputingcollage.CalculatorFragment
import com.scodeid.mobilecomputingcollage.CalcLifeModeFragment

/**
 * @author
 * Created by scode on 23,September,2019
 * Yogi Arif Widodo
 * www.dicoding.com/users/297307
 * www.github.com/yogithesymbian
 * SCODEID company,
 * Indonesia, East Borneo.
 * ==============================================================
Android Studio 3.4.2
Build #AI-183.6156.11.34.5692245, built on June 27, 2019
JRE: 1.8.0_152-release-1343-b16-5323222 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Linux 5.2.0-kali2-amd64
 * ==============================================================
 */

class MainSectionsPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CalculatorFragment()
            }
            1 -> {
                CalcLifeModeFragment()
            }
            else -> CalculatorFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> return "ORDINARY_CALCULATOR"
            1 -> return "ORDINARY_CALCULATOR_1_1"
        }
        return null
    }
}