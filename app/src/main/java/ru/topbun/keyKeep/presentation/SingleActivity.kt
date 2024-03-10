package ru.topbun.keyKeep.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.R

@AndroidEntryPoint
class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        requestFingerPrint()
    }

    private fun requestFingerPrint() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.USE_FINGERPRINT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.USE_FINGERPRINT),
                REQUEST_CODE_PERMISSION
            )
        }
    }


    companion object {
        private const val REQUEST_CODE_PERMISSION = 1001
    }

}