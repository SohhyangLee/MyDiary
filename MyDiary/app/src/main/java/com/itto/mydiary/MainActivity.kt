package com.itto.mydiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itto.mydiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabItemSelectedListener {
    companion object { private final val TAG = "MainActivity" }

    private lateinit var fragment1: Fragment
    private lateinit var fragment2: Fragment
    private lateinit var fragment3: Fragment
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

        mBinding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tab1 -> {
                    Toast.makeText(applicationContext, "select 1st tab", Toast.LENGTH_LONG).show()
                    changeFragment(fragment1)
                }
                R.id.tab2 -> {
                    Toast.makeText(applicationContext, "select 2nd tab", Toast.LENGTH_LONG).show()
                    changeFragment(fragment2)
                }
                R.id.tab3 -> {
                    Toast.makeText(applicationContext, "select 3rd tab", Toast.LENGTH_LONG).show()
                    changeFragment(fragment3)
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onTabSelected(position: Int) {
        when(position){
            0 -> mBinding.bottomNavigation.selectedItemId = R.id.tab1
            1 -> mBinding.bottomNavigation.selectedItemId = R.id.tab2
            2 -> mBinding.bottomNavigation.selectedItemId = R.id.tab3
        }
    }
}
