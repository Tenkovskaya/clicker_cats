package com.tenkovskaya.clicker_cats.fragments.clicker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.contains
import androidx.fragment.app.Fragment
import com.tenkovskaya.clicker_cats.R
import com.tenkovskaya.clicker_cats.`object`.Objects.animation
import com.tenkovskaya.clicker_cats.`object`.Objects.animationCount
import com.tenkovskaya.clicker_cats.`object`.Objects.counter
import com.tenkovskaya.clicker_cats.`object`.Objects.sharedPreferences
import kotlinx.android.synthetic.main.game_layout.view.*

class GameFragment: Fragment() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.game_layout, container, false)
        sharedPreferences = view.context.getSharedPreferences("click_count", Context.MODE_PRIVATE)
        counter = sharedPreferences!!.getInt("click_c", 0)

        //условие о кликах (заменить на нужное число кликов)
        if (counter == 20000) {
            turnTo(EndFragment())
        }

        else {
            //условие о кликах в текстовой части (заменить на нужное число кликов)
            view.count_text.text = "$counter/20000"

            view.setOnTouchListener { v, motionEvent ->
                val x_dot = motionEvent!!.x
                val y_dot = motionEvent.y
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //появление сердца при каждом клике
                        val heart = ImageView(view.context)
                        val layoutParams = LinearLayout.LayoutParams(100, 100)
                        heart.layoutParams = layoutParams
                        heart.background =
                            ResourcesCompat.getDrawable(resources, R.drawable.heart, null)
                        heart.x = x_dot - 50
                        heart.y = y_dot - 100

                        (view as ConstraintLayout).addView(heart)
                        heart.animate().alpha(0F).translationYBy(-150F).setDuration(200)
                            .withEndAction {
                                if (view.contains(heart)) {
                                    view.removeView(heart)
                                }
                            }
                    }
                }
                return@setOnTouchListener false
            }
            animatingOnClick(view)
        }
        return view
    }

    //анимация счетчика и подсчет
    private fun animatingOnClick(view: View) {
        view.setOnClickListener {
            counter++
            animationCount++
            sharedPreferences!!.edit().putInt("click_c", counter).apply()
            view.count_text.text = "$counter/20000"
            if (animation) animateCate(view)
            animation = false
        }
    }


    //анимация кота старт
    private fun animateCate(view: View) {
        if (animation) {
            view.cat1.background =
                ResourcesCompat.getDrawable(resources, R.drawable.cat1, null)
            directAnimation(view)
        }
    }

    //параметры анимации кота
    private fun directAnimation(view: View) {

        //амплитуда движения кота
        view.cat1.animate().rotation(15F).setDuration(450).withEndAction {
            view.cat1.animate().rotation(-15F).setDuration(450).withEndAction {
                if (animationCount != 0) {
                    animationCount = 0
                    directAnimation(view)
                } else {
                    view.cat1.animate().rotation(0F).setDuration(450).withEndAction {
                        if (animationCount != 0) {
                            directAnimation(view)
                            animationCount = 0
                        } else {
                            view.cat1.background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.cat1,
                                    null
                                )
                            animation = true
                        }
                    }
                }

                //возвращение кота на свое место
                view.cat1.animate().rotation(0F).setDuration(450).withEndAction {
                    if (animationCount != 0) {
                        directAnimation(view)
                    } else {
                        view.cat1.background =
                            ResourcesCompat.getDrawable(resources, R.drawable.cat1, null)
                        animation = true
                    }
                    animationCount = 0
                }
            }
        }
    }

//класс ответственный за выполнение действий с фрагментами приложения
    private fun turnTo(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(androidx.navigation.ui.R.anim.nav_default_enter_anim,
                androidx.navigation.ui.R.anim.nav_default_exit_anim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }
}