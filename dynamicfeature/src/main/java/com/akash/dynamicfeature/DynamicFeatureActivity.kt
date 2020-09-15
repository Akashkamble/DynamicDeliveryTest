package com.akash.dynamicfeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.android.synthetic.main.activity_dynamic_feature.*

class DynamicFeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplitCompat.install(this)
        setContentView(R.layout.activity_dynamic_feature)
        button.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }

}