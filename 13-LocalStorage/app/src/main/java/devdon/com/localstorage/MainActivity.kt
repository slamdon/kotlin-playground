package devdon.com.localstorage

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.preference.PreferenceManager
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        saveButton.setOnClickListener(saveButtonClickHandler)
        nextButton.setOnClickListener(nextButtonClickHandler)
    }

    private var saveButtonClickHandler = View.OnClickListener { view ->
        val name = nameEditText.text.toString()
        if(name.isEmpty()){
            Toast.makeText(this,"please type name",Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preference.edit()
        editor.putString("login_name", name)
        editor.apply()

        Toast.makeText(this,"did save name as $name",Toast.LENGTH_SHORT).show()
    }

    private var nextButtonClickHandler = View.OnClickListener { view ->
        val intentNameActivity = Intent(this, NameActivity::class.java)
        startActivity(intentNameActivity)
    }


}

