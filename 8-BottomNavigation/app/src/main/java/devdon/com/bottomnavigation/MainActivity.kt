package devdon.com.bottomnavigation

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.BottomNavigationView
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class FragmentType {
        home, dashboard, notification
    }

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Home"

        initView()
    }

    private fun initView() {
        // set navigation Listener
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // start from Home
        changeFragmentTo(FragmentType.home)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragmentTo(FragmentType.home)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_dashboard -> {
                title = "Dashboard"
                changeFragmentTo(FragmentType.dashboard)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                title = "Notification"
                changeFragmentTo(FragmentType.notification)
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun changeFragmentTo(type: FragmentType) {
        val transaction = manager.beginTransaction()
        when(type) {
            FragmentType.home -> {
                title = "Home"
                val homeFragment = HomeFragment()
                transaction.replace(R.id.baseFragment, homeFragment)
            }

            FragmentType.dashboard -> {
                val dashboardFragment = DashboardFragment()
                transaction.replace(R.id.baseFragment, dashboardFragment)
            }

            FragmentType.notification -> {
                val notificationFragment = NotificationDashboard()
                transaction.replace(R.id.baseFragment, notificationFragment)
            }

        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
