package com.dariushm2.bottomsheet

import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.Transformation

class CircleAnimation(
    private val circle: Circle
) : Animation() {

    init {
        this.interpolator = MyInterpolator()
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        circle.updateCircle(interpolator.getInterpolation(interpolatedTime))
    }

    class MyInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float {
            return (Math.cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.5f
        }
    }
}