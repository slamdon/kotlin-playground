package devdon.com.parks

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager
    private lateinit var adapter:ParksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        // adapter
        adapter = ParksAdapter(supportFragmentManager)

        // LayoutInflater
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // viewPager
        viewPager = findViewById(R.id.layout_main_viewPager)
        viewPager.adapter = adapter

        // tabLayout
        tabLayout = findViewById(R.id.layout_main_tabLayout)

        // link tabLayout with viewPager
        tabLayout.setupWithViewPager(viewPager)
    }

}
