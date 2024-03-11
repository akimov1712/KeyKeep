package ru.topbun.keyKeep.presentation.base

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.ToastLayoutBinding

object CustomToast {

    private var toast: Toast? = null

    fun toastDefault(context: Context, text: String) {
        toast = Toast(context)
        val inflater = LayoutInflater.from(context)
        val binding = ToastLayoutBinding.inflate(inflater, null, false)
        binding.tvTitle.text = text
        toast?.let {
            it.view = binding.root
            it.duration = Toast.LENGTH_SHORT
            it.setGravity(Gravity.TOP, 0, 60)
            it.show()
        }
    }

    fun cancelToast(){
        toast?.cancel()
    }


}