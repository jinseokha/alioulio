package com.alio.ulio.view.ui.main.alarmsend

import android.graphics.Color
import android.graphics.Color.red
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.view.View
import androidx.core.content.ContextCompat

import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.custom.voice.State
import com.alio.ulio.databinding.ActivityVoiceRecoredBinding

import com.alio.ulio.view.ui.main.alarmsend.viewmodel.VoiceRecoredViewModel

import androidx.appcompat.widget.AppCompatButton

import androidx.appcompat.widget.AppCompatEditText

import androidx.appcompat.widget.AppCompatTextView

import android.graphics.drawable.ColorDrawable

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.alio.ulio.R
import com.alio.ulio.custom.voice.SoundVisualizerView


// 3. 음성 녹음 페이지 (뭐라고 보내면 좋을까요? 페이지)
class VoiceRecoredActivity : BaseAppCompatActivity<ActivityVoiceRecoredBinding,
        VoiceRecoredViewModel>(R.layout.activity_voice_recored) {

    private val recordingFilePath : String by lazy {
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }

    private var state = State.BEFORE_RECORDING
        set(value) {
            field = value
            //binding.resetButton.isEnabled = (value == State.AFTER_RECORDING || value == State.ON_PLAYING)
            binding.recordButton.updateIconWithState(value)
        }

    private var recorder : MediaRecorder? = null
    private var player : MediaPlayer? = null


    override fun ActivityVoiceRecoredBinding.onCreate() {
        viewModel = VoiceRecoredViewModel(application)
        binding.viewmodel = viewmodel

        initViews()
        bindViews()
        initVariables()
    }

    private fun initViews() {
        binding.recordButton.updateIconWithState(state)
    }

    private fun bindViews() {
        binding.soundVisualizerView.onRequestCurrentAmplitude = {
            recorder?.maxAmplitude ?: 0
        }

        binding.recordButton.setOnClickListener {
            when (state) {
                State.BEFORE_RECORDING -> {
                    startRecoding()
                }
                State.ON_RECORDING -> {
                    stopRecording()
                }
                /*State.AFTER_RECORDING -> {
                    startPlaying()
                }
                State.ON_PLAYING -> {
                    stopPlaying()
                }*/
            }
        }

        binding.resetButton.setOnClickListener {
            startPlayingDialog()
        }
    }

    private fun initVariables() {
        state = State.BEFORE_RECORDING
    }

    private fun startRecoding() {
        recorder = MediaRecorder()
            .apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(recordingFilePath)
                prepare()
            }
        recorder?.start()
        binding.recordTimeTextView.startCountUp()
        binding.recordTimeTextView.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))
        binding.imgDot.visibility = View.VISIBLE
        binding.soundVisualizerView.startVisualizing(false)
        state = State.ON_RECORDING
    }

    private fun stopRecording() {
        recorder?.run {
            stop()
            release()
        }
        recorder = null
        binding.soundVisualizerView.stopVisualizing()
        binding.recordTimeTextView.stopCountUp()
        binding.imgDot.visibility = View.GONE
        binding.recordTimeTextView.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

        binding.soundVisualizerView.clearVisualization()
        binding.recordTimeTextView.clearCountTime()

        state = State.BEFORE_RECORDING
    }

    private fun startPlaying() {
        player = MediaPlayer()
            .apply {
                setDataSource(recordingFilePath)
                prepare()
            }

        player?.setOnCompletionListener {
            stopPlaying()
            state = State.AFTER_RECORDING
        }

        player?.start()
        binding.recordTimeTextView.stopCountUp()

        binding.soundVisualizerView.startVisualizing(true)

        state = State.ON_PLAYING
    }

    private fun stopPlaying() {
        player?.release()
        player = null
        binding.soundVisualizerView.stopVisualizing()
        binding.recordTimeTextView.stopCountUp()

        state = State.AFTER_RECORDING
    }

    private fun startPlayingDialog() {
        player = MediaPlayer()
            .apply {
                setDataSource(recordingFilePath)
                prepare()
            }

        val layoutInflater = LayoutInflater.from(this)
        val view: View = layoutInflater.inflate(R.layout.dialog_recored_player, null)

        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        alertDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var soundVisualizerView : SoundVisualizerView = view.findViewById(R.id.soundVisualizer)
        var btn_next : AppCompatButton = view.findViewById(R.id.btn_next)

        alertDialog.show()

        soundVisualizerView.onRequestCurrentAmplitude = {
            recorder?.maxAmplitude ?: 0
        }

        player?.setOnCompletionListener {
            player?.release()
            player = null
            soundVisualizerView.stopVisualizing()
            //state = State.AFTER_RECORDING
        }

        player?.start()
        soundVisualizerView.startVisualizing(true)

        btn_next.setOnClickListener { view ->
            alertDialog.dismiss()
        }

        //state = State.ON_PLAYING

    }
}