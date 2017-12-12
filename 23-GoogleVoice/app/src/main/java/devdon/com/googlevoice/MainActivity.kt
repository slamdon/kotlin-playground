package devdon.com.googlevoice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.speech.RecognizerIntent
import android.graphics.Color
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val voiceRecognitionRequestCode = 1004

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == voiceRecognitionRequestCode && resultCode == Activity.RESULT_OK){
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            println("Recognition results $matches")

            // 語音識別會有多個結果，第一個是最精確的
            val text = matches.first()

            voiceTextView.setText(text)
            updateTextViewWithText(text)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupView() {
        recognitionButton.setOnClickListener(recognitionButtonListener)
        tipsTextView.setTextColor(Color.WHITE)

    }

    private fun startVoiceRecognitionActivity() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please say something")

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)

        startActivityForResult(intent, voiceRecognitionRequestCode)
    }

    private val recognitionButtonListener = View.OnClickListener { view ->
        startVoiceRecognitionActivity()
    }

    // 根據語音識別內容改變文字顏色
    private fun updateTextViewWithText(text:String) {
        Log.e("update:", text)
        bikeTextView.setTextColor(Color.BLACK)
        climbTextView.setTextColor(Color.BLACK)
        swimTextView.setTextColor(Color.BLACK)

        // 平時 tips 用白色隱藏，除非使用者說的內容不在範圍才用黑色顯示
        tipsTextView.setTextColor(Color.WHITE)

        if(text.contains("騎車",true)){
            bikeTextView.setTextColor(Color.BLUE)

        } else if(text.contains("爬山", false)) {
            climbTextView.setTextColor(Color.BLUE)

        } else if(text.contains("游泳", false)){
            swimTextView.setTextColor(Color.BLUE)

        } else {
            tipsTextView.setTextColor(Color.BLUE)
        }

    }



}
