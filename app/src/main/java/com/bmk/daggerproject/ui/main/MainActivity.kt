package com.bmk.daggerproject.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.ActivityMainBinding
import com.bmk.daggerproject.ui.about.PlayerFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setView()
    }

    private fun setView() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(
                AnimType.SLIDE.getAnimPair().first,
                AnimType.SLIDE.getAnimPair().second
            )
            .replace(R.id.frame, PlayerFragment.newInstance(), PlayerFragment.TAG)
            .commit()
    }


    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }

    enum class AnimType() {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            return when (this) {
                SLIDE -> Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> Pair(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}