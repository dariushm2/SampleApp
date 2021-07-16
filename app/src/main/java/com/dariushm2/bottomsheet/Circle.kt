package com.dariushm2.bottomsheet

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation


class Circle(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint
    private val rect: RectF
    var angle: Float
        private set

    private var isClearingCanvas = false
    private var bgColor: Int = 0

    companion object {
        private const val START_ANGLE_POINT = 270F
    }

    init {
        val strokeWidth = 8F
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        //Circle color
        paint.color = Color.GREEN

        //size 200x200 example
        rect = RectF(
            strokeWidth,
            strokeWidth,
            (convertDpToPixels(100F).minus(strokeWidth)),
            (convertDpToPixels(100F).minus(strokeWidth))
        )

        //Initial Angle (optional, it can be zero)
        angle = 0F
    }

    fun updateCircle(newAngle: Float) {
        angle = newAngle
        requestLayout()
    }

    fun reset(bgColor: Int) {
        isClearingCanvas = true
        this.bgColor = bgColor
        angle = 0F
        requestLayout()
    }

    fun anim(stiffness: Float, dampingRatio: Float) {
        Log.e("delay", "animate")
//        val animation = CircleAnimation(this)
//        this.startAnimation(animation)

        SpringAnimation(this, ParamYProp(), 365F).apply {
            spring.stiffness = stiffness
            spring.dampingRatio = dampingRatio
            start()
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isClearingCanvas) {
            canvas.drawColor(ContextCompat.getColor(context, bgColor))
            isClearingCanvas = false
        } else {
            canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint)
        }
    }

    private inner class ParamYProp : FloatPropertyCompat<Circle>("ParamY") {

        override fun setValue(view: Circle, animValue: Float) {
            Log.e("circle ParamY", animValue.toString())
            view.updateCircle(animValue)
        }

        override fun getValue(view: Circle): Float = view.angle
    }

    private fun convertDpToPixels(dp: Float): Float = dp * resources.displayMetrics.density
}