package com.scodeid.mobilecomputingcollage

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.scodeid.mobilecomputingcollage.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main_calc_content.*

class MainCalcActivity : AppCompatActivity() {
    private var sectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calc)

        /**
         * CUSTOM TOOLBAR
         */
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        /**
         * END OF CUSTOM TOOLBAR
         */

        /**
         * SET VIEW PAGER ADAPTER
         */
        sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container_main_calc.adapter = sectionsPagerAdapter
        /**
         * END OF SET VIEW PAGER ADAPTER
         */

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_calc, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this@MainCalcActivity, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
