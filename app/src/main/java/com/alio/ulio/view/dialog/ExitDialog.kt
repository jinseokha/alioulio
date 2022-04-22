package com.alio.ulio.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.alio.ulio.databinding.DialogExitBinding

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-28
 * @desc
 */
class ExitDialog(private val context : AppCompatActivity) {

    private lateinit var binding : DialogExitBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var listener : MyDialogOKClickedListener

    fun show() {
        binding = DialogExitBinding.inflate(context.layoutInflater)

        dlg.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        //ok 버튼 동작
        binding.ok.setOnClickListener {

            dlg.dismiss()
            context.finish()
        }

        //cancel 버튼 동작
        binding.cancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }

    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }


    interface MyDialogOKClickedListener {
        fun onOKClicked(content : String)
    }

}
