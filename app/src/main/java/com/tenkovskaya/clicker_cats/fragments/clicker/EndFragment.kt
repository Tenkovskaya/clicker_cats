package com.tenkovskaya.clicker_cats.fragments.clicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenkovskaya.clicker_cats.R
import kotlinx.android.synthetic.main.end_layout.view.*

class EndFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.end_layout, container, false)
        view.menu_b.setOnClickListener {
            backToStart(StartFragment())
        }
        return view
    }
    private fun backToStart(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(androidx.navigation.ui.R.anim.nav_default_enter_anim,
                androidx.navigation.ui.R.anim.nav_default_exit_anim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }
}