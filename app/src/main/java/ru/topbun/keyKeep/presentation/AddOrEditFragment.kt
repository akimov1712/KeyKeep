package ru.topbun.keyKeep.presentation

import android.text.InputType
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.FragmentAddOrEditBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment

@AndroidEntryPoint
class AddOrEditFragment :BaseFragment<FragmentAddOrEditBinding>(FragmentAddOrEditBinding::inflate){

    private var isShowPassword = false

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            btnBack.setOnClickListener { findNavController().popBackStack() }
            btnShowPassword.setOnClickListener {
                if(isShowPassword) {
                    etPassword.inputType = TYPE_PASSWORD_VISIBLE
                    btnShowPassword.setImageResource(R.drawable.ic_eye_close)
                } else {
                    etPassword.inputType = InputType.TYPE_CLASS_TEXT
                    btnShowPassword.setImageResource(R.drawable.ic_eye_show)
                }
                isShowPassword = !isShowPassword
            }
        }
    }

    companion object{
        private const val TYPE_PASSWORD_VISIBLE = 129
    }

}
