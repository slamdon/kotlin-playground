package devdon.com.bottomnavigation


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        homeButton.setOnClickListener(homeButtonOnClickHandler)
    }

    private var homeButtonOnClickHandler = View.OnClickListener { view ->
        val intentHomeDetailActivity = Intent(activity,Home2Activity::class.java)
        startActivity(intentHomeDetailActivity)
    }

}

