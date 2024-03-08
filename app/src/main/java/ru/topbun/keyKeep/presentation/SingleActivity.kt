package ru.topbun.keyKeep.presentation

import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.R

@AndroidEntryPoint
class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
    }
}