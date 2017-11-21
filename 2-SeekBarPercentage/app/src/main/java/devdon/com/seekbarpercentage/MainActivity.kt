package devdon.com.seekbarpercentage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var value = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        // progressBar
        progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value = priceEditText.text.toString().removePrefix("$").toFloatOrNull() ?: 0f
                percentTextView.text = "打折（$p1%)"
                calculateResult()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        priceEditText.setOnEditorActionListener{ textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                value = priceEditText.text.toString().removePrefix("$").toFloatOrNull() ?: 0f
                priceEditText.setText("$$value")
                calculateResult()
            }

            // false 表示收起鍵盤（不保留鍵盤）
            priceEditText.clearFocus()
            false

        }

    }

    private fun calculateResult() {
        val discount = progressBar.progress * value / 100
        val total = value - discount
        discountTextView.text = String.format("%.2f", discount)
        resultTextView.text = String.format("%.2f", total)
    }
}

