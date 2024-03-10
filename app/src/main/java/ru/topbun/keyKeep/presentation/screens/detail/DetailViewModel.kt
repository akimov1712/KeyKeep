package ru.topbun.keyKeep.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.domain.useCases.password.AddPasswordUseCase
import ru.topbun.keyKeep.domain.useCases.password.DeletePasswordUseCase
import ru.topbun.keyKeep.domain.useCases.password.GetPasswordWithIdUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPasswordWithIdUseCase: GetPasswordWithIdUseCase,
    private val addPasswordUseCase: AddPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Initial)
    val state get() = _state.asStateFlow()

    fun addPassword(
        name: String?,email: String?,
        site: String, password: String, id: Int = 0
    ) = viewModelScope.launch {
        val parseName = name.parseString()
        val parseEmail = email.parseString()
        val parseSite = site.parseString()
        val parsePassword = password.parseString()
        val isValid = validData(parseName, parseSite, parsePassword)
        if (isValid){
            addPasswordUseCase(
                PasswordEntity(
                    id = id,
                    name = parseName,
                    site = parseSite,
                    email = parseEmail,
                    password = parsePassword
                )
            )
            _state.emit(DetailState.ShouldCloseScreen)
        }
    }

    private suspend fun validData(
        name: String, site: String, password: String
    ): Boolean{
        if (name.isBlank()){
            _state.emit(DetailState.ErrorValidData("Поле с именем не может быть пустым"))
            return false
        }
        if (site.isBlank()){
            _state.emit(DetailState.ErrorValidData("Поле с сайтом не может быть пустым"))
            return false
        }
        if (password.isBlank()){
            _state.emit(DetailState.ErrorValidData("Поле с паролем не может быть пустым"))
            return false
        }
        return true
    }

    private fun String?.parseString() = this?.trim() ?: ""

    fun getPassword(id: Int) = viewModelScope.launch {
        val item = getPasswordWithIdUseCase(id)
        _state.emit(DetailState.PasswordItem(item))
    }

}