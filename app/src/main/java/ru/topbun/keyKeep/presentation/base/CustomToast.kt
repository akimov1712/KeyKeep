package ru.topbun.keyKeep.presentation.base

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import ru.topbun.keyKeep.R

object CustomToast {

    private var toast: Toast? = null

    fun toastDefault(context: Context, text: String) {
        toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(
            R.layout.toast_layout,
            null,
            false
        )
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        tvTitle.text = text
        toast?.let {
            it.view = view
            it.duration = Toast.LENGTH_SHORT
            it.setGravity(Gravity.TOP, 0, 60)
            it.show()
        }
    }

    fun cancelToast(){
        toast?.cancel()
    }


}