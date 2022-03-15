package com.example.youbank

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_YouBank_NoActionBar)
        // FLAG_SECURE hides the view when the Recents Screen(Multitasking Pane) is active on android
        // The action when you press the middle button on 3-button navigation, or swipe up and hold without buttons
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar))
    }
}