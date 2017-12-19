package devdon.com.videoplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.view.View
import android.widget.SeekBar
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var videoView:VideoView
    private var isSeeking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupVideoView()
    }


    private fun setupView() {
        main_playButton.setOnClickListener(playButtonClickHandler)
        main_pauseButton.setOnClickListener(pauseButtonClickHandler)
        main_stopButton.setOnClickListener(stopButtonClickHandler)
    }

    private fun setupVideoView() {
        videoView = findViewById(R.id.layout_video_view)
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.short_video))
        videoView.start()

        // hide medie controller
        videoView.setMediaController(null)
    }

    private val playButtonClickHandler = View.OnClickListener { _ ->
        videoView.start()
    }

    private val pauseButtonClickHandler = View.OnClickListener { _ ->
        videoView.pause()
    }

    private val stopButtonClickHandler = View.OnClickListener { _ ->
        videoView.seekTo(0)
        videoView.pause()
    }


}
