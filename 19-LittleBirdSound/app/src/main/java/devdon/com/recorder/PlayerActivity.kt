package devdon.com.recorder

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var birdAnimator:ObjectAnimator

    private var isSeeking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        title = "Bird Records"

        setupView()
    }

    override fun onPause() {
        super.onPause()

        stopPlayer()
        resetAnimateBirdImageView()
    }

    private fun setupView() {
        // setup mediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_bird)

        // setup Animator
        birdAnimator = ObjectAnimator.ofFloat(birdImageView, "rotation", 0.0f, 360f)
        birdAnimator.duration = 3000
        birdAnimator.repeatCount = ObjectAnimator.INFINITE
        birdAnimator.repeatMode = ObjectAnimator.RESTART

        // total time duration of sonng
        progressSeekBar.max = mediaPlayer.duration

        // set default sound level
        mediaPlayer.setVolume(25 / 100f, 25 / 100f)

        // set listener
        playButton.setOnClickListener(playButtonClickHandler)
        stopButton.setOnClickListener(stopButtonClickHandler)

        progressSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                if(isSeeking) {
                    mediaPlayer.seekTo(progressSeekBar.progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                isSeeking = true
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                isSeeking = false
            }

        })

        volumeSeekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                // update progress text
                volumeTextView.setText("Volumn: ${volumeSeekBar.progress}%")

                // update volumn
                mediaPlayer.setVolume(progress / 100f, progress / 100f)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        mediaPlayer.setOnCompletionListener {
            stopPlayer()
        }

        // continuously updating progress
        val thread = Thread(Runnable {
            while (true) {
                Thread.sleep(500)
                if (!isSeeking) {
                    progressSeekBar.progress = mediaPlayer.currentPosition
                }
            }
        })
        thread.start()


    }

    private var playButtonClickHandler = View.OnClickListener { view ->
        if(mediaPlayer.isPlaying){
            pausePlayer()
        } else {
            startPlayer()
        }
    }

    private var stopButtonClickHandler = View.OnClickListener { view ->
        stopPlayer()
    }


    private fun startPlayer() {
        // 如果播放完畢，則從頭開始播放
        if(mediaPlayer.currentPosition == mediaPlayer.duration){
            mediaPlayer.seekTo(0)
        }

        mediaPlayer.start()
        playButton.setText("PAUSE")

        startAnimateBirdImageView()
    }

    private fun pausePlayer() {
        mediaPlayer.pause()

        playButton.setText("PLAY")

        pauseAnimateBirdImageView()
    }

    private fun stopPlayer() {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0)

        progressSeekBar.progress = 0

        playButton.setText("PLAY")

        resetAnimateBirdImageView()
    }

    private fun startAnimateBirdImageView() {
        if(birdAnimator.isPaused){
            birdAnimator.resume()
        } else {
            birdAnimator.start()
        }
    }

    private fun pauseAnimateBirdImageView() {
        birdAnimator.pause()

    }

    private fun resetAnimateBirdImageView() {
        birdAnimator.end()
    }



}
