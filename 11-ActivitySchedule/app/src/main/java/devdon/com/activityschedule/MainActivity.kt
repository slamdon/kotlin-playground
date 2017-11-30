package devdon.com.activityschedule

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()

    private val timeSetListener = TimePickerDialog.OnTimeSetListener{ view, hour, minute ->

        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

        val time = SimpleDateFormat("HH:mm", Locale.TAIWAN)
        timeTextView.text = time.format(cal.time)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, date ->
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DATE, date)

        val time = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)
        dateTextView.text = time.format(cal.time)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {

        // set listener
        dateTextView.setOnClickListener(dateTextViewHandler)
        timeTextView.setOnClickListener(timeTextViewHandler)
        goButton.setOnClickListener(goButtonHandler)
    }


    private var dateTextViewHandler = View.OnClickListener { view ->
        println("year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} date ${cal.get(Calendar.DATE)}")
        DatePickerDialog(this,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE)
        ).show()

    }

    private var timeTextViewHandler = View.OnClickListener { view ->
        println("Hour ${cal.get(Calendar.HOUR)} Minute ${cal.get(Calendar.MINUTE)}")

        TimePickerDialog(this, timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
        ).show()
    }

    private var goButtonHandler = View.OnClickListener { view ->
        showConfirmDialog()

    }

    private fun showConfirmDialog() {
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN).format(cal.time)

        // setup dialog builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Party time confirm")
        builder.setMessage("$time")
        builder.setPositiveButton("Confirm",{ dialog, whichButton ->
            println("confirm")
        })

        builder.setNegativeButton("Cancel", { dialog, whichButton ->
            println("cancel")
        })

        // create dialog and show it
        val dialog = builder.create()
        dialog.show()
    }

}
