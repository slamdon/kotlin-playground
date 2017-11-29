package devdon.com.progresscontroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentProgress:Int = 0
    private var maxProgress = 100
    private var repeatTaskTime:Long = 500

    private var taskHandler = Handler()
    private var runnable = object:Runnable{
        override fun run() {
            startIncreaseProgress()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        startButton.setOnClickListener(startButtonHandler)
        pauseButton.setOnClickListener(pauseButtonHandler)
        stopButton.setOnClickListener(stopbuttonHandler)

        // setup progress
        progressBar.max = 100
        progressBar.progress = 0

    }

    private var startButtonHandler = View.OnClickListener { view ->
        startIncreaseProgress()
    }


    private var pauseButtonHandler = View.OnClickListener { view ->
        pauseIncreasingProgress()
    }

    private var stopbuttonHandler = View.OnClickListener { view ->
        resetProgress()
    }

    private fun startIncreaseProgress() {
        if(currentProgress >= maxProgress){
            pauseIncreasingProgress()
            return

        } else {

            increaseProgressBy(currentProgress)
        }

        println("post runnable")
        taskHandler.postDelayed(runnable,repeatTaskTime)


    }

    private fun increaseProgressBy(value:Int) {
        println("remove task handler")

        currentProgress += 10
        progressBar.setProgress(currentProgress)

        updateProgressTextView()
    }

    private fun pauseIncreasingProgress() {
        println("remove task handler")

        taskHandler.removeCallbacksAndMessages(null)
    }

    private fun resetProgress() {
        println("reset progress")

        pauseIncreasingProgress()

        currentProgress = 0
        progressBar.setProgress(0)

        updateProgressTextView()
    }

    private fun updateProgressTextView() {
        progressTextView.text = "$currentProgress%"
    }
}
