package devdon.com.sidemenu

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupView()
    }

    private fun setupView() {
        // init
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nvView)

        // set navigation select Listener
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener)

        // set a toolbar to replace the actionBar
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        setSupportActionBar(toolbar)

        // set default fragment
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.flContent, Chicken1Fragment()).commit()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private var navigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { menuItem ->
        Log.e("selectDrawerItem","on ${menuItem.itemId}")


        when(menuItem.itemId) {
            R.id.nav_1_fragment -> {
                val fragment = Chicken1Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()

            }

            R.id.nav_2_fragment -> {
                val fragment = Chicken2Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }

            R.id.nav_3_fragment -> {
                val fragment = Chicken3Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }

            R.id.nav_4_fragment -> {
                val fragment = Chicken4Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }

            R.id.nav_5_fragment -> {
                val fragment = Chicken5Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }

            R.id.nav_6_fragment -> {
                val fragment = Chicken6Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }

            R.id.nav_7_fragment -> {
                val fragment = Chicken7Fragment()
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
            }
        }


        menuItem.setChecked(true)

        setTitle(menuItem.title)

        drawer.closeDrawers()

        true

    }

}
