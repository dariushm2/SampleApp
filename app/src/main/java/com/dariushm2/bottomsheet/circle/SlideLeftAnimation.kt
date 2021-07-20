package com.dariushm2.bottomsheet.circle

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation

class SlideLeftAnimation(
    private val view: View
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        SpringAnimation(view, ParamYProp(), 0F).apply {
            spring.stiffness = 100F
            spring.dampingRatio = 0.9F
            setStartVelocity(50F)
            start()
        }
    }

    private inner class ParamYProp : FloatPropertyCompat<View>("ParamY") {

        override fun setValue(view: View, animValue: Float) {
            view.translationX = animValue
        }

        override fun getValue(view: View): Float = view.translationX - 100
    }
}