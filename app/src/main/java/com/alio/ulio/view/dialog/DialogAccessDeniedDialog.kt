package com.alio.ulio.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.alio.ulio.databinding.DialogAccessDeniedBinding

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-13
 * @desc
 */
class DialogAccessDeniedDialog : DialogFragment() {

    private lateinit var binding : DialogAccessDeniedBinding
    private lateinit var clicklistener : DialogClickListener

    fun setDialogListener(click: DialogClickListener) {
        this.clicklistener = click
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAccessDeniedBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        binding.btnNotAgree.setOnClickListener {
            clicklistener.onNotAgreeClick()
            dismiss()
        }

        binding.btnAgree.setOnClickListener {
            clicklistener.onAgreeClicked()
            dismiss()
        }

        return binding.root
    }

    interface DialogClickListener {
        fun onAgreeClicked()
        fun onNotAgreeClick()
    }
}