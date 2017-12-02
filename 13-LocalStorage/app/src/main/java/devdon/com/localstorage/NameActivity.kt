package devdon.com.localstorage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        setupView()

    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showNameButton.setOnClickListener(showNameButtonClickHandler)
    }

    private var showNameButtonClickHandler = View.OnClickListener { view ->
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val name = preference.getString("login_name","")
        if(name.isEmpty()){
            Toast.makeText(this,"name is empty",Toast.LENGTH_SHORT).show()
        }
        nameTextView.text = name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }


}

