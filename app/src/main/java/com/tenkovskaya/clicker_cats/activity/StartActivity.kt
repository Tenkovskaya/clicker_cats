package com.tenkovskaya.clicker_cats.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tenkovskaya.clicker_cats.R
import com.tenkovskaya.clicker_cats.fragments.clicker.StartFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, StartFragment())
            .commit()
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.fragments[0] is StartFragment -> {
                finish()
            }
            else -> {
                supportFragmentManager.fragments[0].onDestroy()
                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(androidx.navigation.ui.R.anim.nav_default_enter_anim,
                        androidx.navigation.ui.R.anim.nav_default_exit_anim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                    .addToBackStack(null)
                    .replace(R.id.container, StartFragment())
                    .commit()
            }
        }
    }
}
