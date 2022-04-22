package com.alio.ulio.view.dialog

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.alio.ulio.custom.voice.State
import com.alio.ulio.databinding.DialogRecoredPlayerBinding

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-21
 * @desc
 */
class DialogRecoredPlayer(filePath : String) : DialogFragment() {

    private lateinit var binding : DialogRecoredPlayerBinding
    private lateinit var clicklistener : DialogClickListener

    private lateinit var mediaPlayer: MediaPlayer

    var path = filePath

    private var state = State.ON_PLAYING
        set(value) {
            field = value
            //binding.resetButton.isEnabled = (value == State.AFTER_RECORDING || value == State.ON_PLAYING)
        }

    fun setDialogListener(click : DialogClickListener) {
        this.clicklistener = click
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), Uri.parse(path))
            .apply { isLooping = true }

        /*mediaPlayer = MediaPlayer()
            .apply {
                setDataSource(path)
                prepare()
                isLooping = true
            }*/
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogRecoredPlayerBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        togglePlayBack()

        binding.btnNext.setOnClickListener {
            //stopPlaying()
            dismiss()
        }

        /*player = MediaPlayer()
            .apply {
                setDataSource(path)
                prepare()
            }

        player?.setOnCompletionListener {
            stopPlaying()
        }

        player?.start()
        binding.soundVisualizer.startVisualizing(true)*/

        return binding.root
    }

    private fun togglePlayBack() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            checkAudioRecordPermission {
                mediaPlayer.start()
                binding.soundVisualizer.link(mediaPlayer)
            }
        }
    }

    private fun checkAudioRecordPermission(block: () -> Unit) {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.RECORD_AUDIO
                )
            ) {
                //showToast(R.string.no_permission)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    PERMISSION_REQUEST_CODE
                )
            }
        } else {
            block()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    togglePlayBack()
                } else {
                    //showToast(R.string.no_permission)
                }
                return
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
    /*private fun stopPlaying() {
        player?.release()
        player = null
        binding.soundVisualizer.stopVisualizing()

        state = State.AFTER_RECORDING
        Toast.makeText(requireActivity(), "끝", Toast.LENGTH_LONG).show()
    }*/

    interface DialogClickListener {
        fun onNextClicked()
    }
}