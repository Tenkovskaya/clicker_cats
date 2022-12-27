package com.tenkovskaya.clicker_cats.fragments.clicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenkovskaya.clicker_cats.R
import com.tenkovskaya.clicker_cats.fragments.to_do_list.StartToDo
import kotlinx.android.synthetic.main.start_layout.view.*

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.start_layout, container, false)


        view.play_b3.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .setCustomAnimations(androidx.navigation.ui.R.anim.nav_default_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_exit_anim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .addToBackStack(null)
                .replace(R.id.container, GameFragment())
                .commit()
        }


        view.to_do_bt.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .setCustomAnimations(androidx.navigation.ui.R.anim.nav_default_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_exit_anim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .addToBackStack(null)
                .replace(R.id.container, StartToDo())
                .commit()
        }

        return view
    }

}

