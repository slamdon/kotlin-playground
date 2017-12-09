package devdon.com.recorder

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_record.*
import android.media.MediaRecorder
import android.media.MediaPlayer
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.IOException


class RecordActivity : AppCompatActivity() {

    var soundFile: File? = null
    var isRecording = false

    lateinit var recorder: MediaRecorder
    var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        title = "Recorder"
        setupView()
    }

    private fun setupView() {
        recordButton.setOnClickListener(recordButtonClickHandler)
        playButton.setOnClickListener(playButtonClickHandler)
    }

    private val recordButtonClickHandler = View.OnClickListener { view ->
        if(isRecording){
            Toast.makeText(this,"Stop Recording", Toast.LENGTH_SHORT).show()
            stopRecording()
        } else {
            Toast.makeText(this,"Start Recording", Toast.LENGTH_SHORT).show()
            startRecording()
        }
    }

    private val playButtonClickHandler = View.OnClickListener { view ->
        if(soundFile == null){
            Toast.makeText(this, "Found sound file is null when click play", Toast.LENGTH_SHORT).show()

        } else {
            // setup player
            player = MediaPlayer()
            player!!.setOnPreparedListener(playerPreparedHandler)
            player!!.setOnCompletionListener {
                stopPlayer()
            }

            if(player!!.isPlaying){
                player?.pause()
                playButton.setText("Play")
            } else {
                playButton.setText("Pause")
                try {
                    player?.setDataSource(soundFile!!.absolutePath)
                    player?.prepare()

                } catch(e: IOException) {
                    Log.e("PlayRecording","Failed")
                }
            }
        }
    }


    private fun startRecording() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
            return
        }

        // setup file
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
            return
        } else {
            val path = File(Environment.getExternalStorageDirectory().path)
            try {
                soundFile = File.createTempFile ("birdRecording", ".3gp", path)
                println("created file $soundFile")

            } catch (e: IOException) {
                Log.e("Setup sound File","failed ${e.message}")
            }
        }

        if(soundFile == null){
            Toast.makeText(this, "Sound file is null", Toast.LENGTH_SHORT).show()
            return
        }

        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        recorder.setOutputFile(soundFile?.absolutePath)
        try {
            recorder.prepare()
        } catch (e: IOException) {

        }

        recorder.start()

        isRecording = true
        playButton.isEnabled = false
        recordButton.setText("Stop Record")

    }

    private fun stopRecording() {
        Log.e("Recorder","stop recording")

        recorder.stop()
        recorder.release()

        isRecording = false
        playButton.isEnabled = true
        recordButton.setText("Record")
    }

    private var playerPreparedHandler = MediaPlayer.OnPreparedListener {
        player?.start()
    }

    private fun stopPlayer() {
        player?.release()
        player = null
        playButton.setText("PLAY")

    }

}
