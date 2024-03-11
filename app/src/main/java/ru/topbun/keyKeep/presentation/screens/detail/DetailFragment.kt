package ru.topbun.keyKeep.presentation.screens.detail

import android.text.InputType
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.Const
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.FragmentDetailBinding
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.presentation.base.BaseFragment
import ru.topbun.keyKeep.presentation.base.CustomToast

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()

    private var isShowPassword = false
    private var passwordItem: PasswordEntity? = null

    override fun setViews() {
        super.setViews()
        defineMode()
    }

    private fun defineMode() {
        with(binding) {
            if (args.idPassword == Const.UNDEFINED_ID) {
                val textMode = requireContext().getString(R.string.add)
                btnDelete.visibility = View.GONE
                btnAddOrEdit.text = textMode
                tvTitle.text = textMode
            } else {
                val textMode = requireContext().getString(R.string.edit)
                viewModel.getPassword(args.idPassword)
                btnDelete.visibility = View.VISIBLE
                btnAddOrEdit.text = textMode
                tvTitle.text = textMode
            }
        }
    }

    private fun FragmentDetailBinding.setInfoPasswordInView(password: PasswordEntity) {
        etName.setText(password.name)
        etEmail.setText(password.email)
        etSite.setText(password.site)
        etPassword.setText(password.password)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collect {
                        when (it) {
                            is DetailState.PasswordItem -> {
                                passwordItem = it.item
                                setInfoPasswordInView(it.item)
                            }
                            is DetailState.ErrorValidData -> {
                                CustomToast.toastDefault(requireContext(), it.message)
                            }

                            is DetailState.ShouldCloseScreen -> {
                                findNavController().popBackStack()
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding) {
            btnBack.setOnClickListener { findNavController().popBackStack() }
            btnShowPassword.setOnClickListener {
                if (isShowPassword) {
                    etPassword.inputType = TYPE_PASSWORD_VISIBLE
                    btnShowPassword.setImageResource(R.drawable.ic_eye_close)
                } else {
                    etPassword.inputType = InputType.TYPE_CLASS_TEXT
                    btnShowPassword.setImageResource(R.drawable.ic_eye_show)
                }
                isShowPassword = !isShowPassword
            }
            btnAddOrEdit.setOnClickListener {
                val name = etName.text.trim().toString()
                val site = etSite.text.trim().toString()
                val email = etEmail.text.trim().toString()
                val password = etPassword.text.trim().toString()
                viewModel.addPassword(name, email, site, password, args.idPassword)
            }
            btnDelete.setOnClickListener {
                passwordItem?.let {
                    findNavController().navigate(
                        DetailFragmentDirections.actionAddOrEditFragmentToConfirmDeleteDialog(
                            it.id,
                            it.name
                        )
                    )
                }

            }
        }
    }

    companion object {
        private const val TYPE_PASSWORD_VISIBLE = 129
    }

}
