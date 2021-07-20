package com.dariushm2.bottomsheet.circle

import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation

class ScaleUpAnimation(
    private val view: View
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        duration = 1500L
        SpringAnimation(view, ParamYProp(), 100F).apply {
            spring.stiffness = 30F
            spring.dampingRatio = 0.9F
            view.scaleX = 0.5F
            view.scaleY = 0.5F
            start()
        }
    }

    private inner class ParamYProp : FloatPropertyCompat<View>("ParamY") {

        override fun setValue(view: View, animValue: Float) {
            Log.e("circle scale up", view.scaleY.toString())
            view.scaleY = animValue.div(100)
            view.scaleX = animValue.div(100)
        }

        override fun getValue(view: View): Float {
            return view.scaleY
        }
    }
}