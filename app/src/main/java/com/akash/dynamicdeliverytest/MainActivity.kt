package com.akash.dynamicdeliverytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SplitInstallStateUpdatedListener {

    private lateinit var installManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        installManager = FakeSplitInstallManagerFactory.create(applicationContext)
        installManager.registerListener(this)
        tv_hello_world.setOnClickListener {
            installManager.startInstall(
                SplitInstallRequest.newBuilder()
                    .addModule("dynamicfeature")
                    .build()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        installManager.unregisterListener(this)
    }


    fun getClassName(): Class<*>? {
        return try {
            Class.forName("com.akash.dynamicfeature.DynamicFeatureActivity")
        } catch (e: ClassNotFoundException) {
            null
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(newBase)
    }

    override fun onStateUpdate(state: SplitInstallSessionState) {
        when (state.status()) {
            SplitInstallSessionStatus.INSTALLED -> {
                showShortToast("Installed")
                getClassName()?.let { activityClass ->
                    startActivity(Intent(this, activityClass))
                }
            }
            SplitInstallSessionStatus.CANCELED -> {
                showShortToast("Canceled")
            }
            SplitInstallSessionStatus.CANCELING -> {
                showShortToast("Canceling")
            }
            SplitInstallSessionStatus.DOWNLOADED -> {
                showShortToast("Downloaded")
            }
            SplitInstallSessionStatus.DOWNLOADING -> {
                showShortToast("Downloading")
            }
            SplitInstallSessionStatus.FAILED -> {
                showShortToast("Failed")
            }
            SplitInstallSessionStatus.INSTALLING -> {
                showShortToast("Installing")
            }
            SplitInstallSessionStatus.PENDING -> {
                showShortToast("Pending")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                showShortToast("Requires user confirmation")
                installManager.startConfirmationDialogForResult(
                    state, this, 123
                )
            }
            SplitInstallSessionStatus.UNKNOWN -> {
                showShortToast("Unknown state")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            showShortToast("Result from user confirmation")
        }
        if (requestCode == 345) {
            installManager.deferredUninstall(listOf("dynamicfeature"))
        }
    }
}