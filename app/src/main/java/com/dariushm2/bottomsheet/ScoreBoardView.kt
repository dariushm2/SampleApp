package com.dariushm2.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import kotlin.properties.Delegates


class ScoreBoardView : FrameLayout {

    companion object {
        private const val ANIMATION_DURATION = 250L
        private enum class Direction {
            INCREASE,
            DECREASE
        }
    }

    private lateinit var txtOnes: TextView
    private lateinit var txtTens: TextView
    private lateinit var txtHundreds: TextView
    private var initialTranslationY by Delegates.notNull<Float>()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    private var currentValue = 0

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.score_board_view, this)
        txtOnes = rootView.findViewById(R.id.txt_ones)
        txtTens = rootView.findViewById(R.id.txt_tens)
        txtHundreds = rootView.findViewById(R.id.txt_hundreds)
        initialTranslationY = txtOnes.translationY
    }

    fun setValue(desiredValue: Int) {

        if (desiredValue > 999 || desiredValue < 0) return

        txtOnes.show(true)
        txtTens.show(desiredValue.div(10) > 0)
        txtHundreds.show(desiredValue.div(100) > 0)

        val delta = desiredValue - currentValue
        val direction = if (delta > 0) Direction.INCREASE else Direction.DECREASE
        when {
            currentValue.plus(delta) < 10 -> {
                springAnimation(txtOnes, currentValue.plus(delta), direction)
            }
            currentValue.plus(delta) < 100 -> {
                springAnimation(txtOnes, currentValue.plus(delta).rem(10), direction)
                springAnimation(txtTens, currentValue.plus(delta).div(10), direction)
            }
            else -> {
                springAnimation(txtOnes, currentValue.plus(delta).rem(10), direction)
                springAnimation(txtTens, currentValue.plus(delta).rem(100).div(10), direction)
                springAnimation(txtHundreds, currentValue.plus(delta).div(100), direction)
            }
        }
        currentValue = desiredValue
    }

    private fun TextView.show(shouldShow: Boolean) {
        visibility = if (shouldShow) View.VISIBLE else View.GONE
        if (!shouldShow) text = 0.toString()
    }

    private fun TextView.getValue(): Int = if (text.isNullOrEmpty()) 0 else text.toString().toInt()

    private fun springAnimation(textView: TextView, newValue: Int, direction: Direction) {
        if (textView.text == newValue.toString()) return
        val factor = if (direction == Direction.INCREASE) -1 else 1
        textView.translationY = initialTranslationY // To set it to its original position in case animation was interrupted for some reason
        SpringAnimation(textView, ParamYProp(), factor * height.toFloat())
            .apply {
            spring.stiffness = 500F
            spring.dampingRatio = 0.9F

            addEndListener { _, _, _, _ ->

                textView.translationY = (factor * -1) * height.toFloat()
                textView.text = newValue.toString()
                SpringAnimation(textView, ParamYProp(), initialTranslationY)
                    .apply {
                        spring.stiffness = 500F
                        spring.dampingRatio = 0.9F

                        start()
                    }
            }
            start()
        }
    }

    inner class ParamYProp : FloatPropertyCompat<TextView>("") {
        override fun setValue(textView: TextView, animValue: Float) {
            textView.translationY = animValue
        }

        override fun getValue(textView: TextView): Float = textView.translationY
    }

//    private fun changeValueWithAnimation(newValue: Int) {
//        val oldValue = onesCurrentValue.text.toString().toInt()
//        val direction = if (newValue > oldValue) -1 else 1
//
//        onesNextValue.text = String.format(Locale.getDefault(), "%d", newValue)
//        onesCurrentValue.animate().translationY(direction * height.toFloat()).setDuration(
//            ANIMATION_DURATION
//        ).start()
//        onesNextValue.translationY = (direction * -1) * onesNextValue.height.toFloat()
//        onesNextValue.animate()
//            .translationY(0f)
//            .setDuration(ANIMATION_DURATION)
//            .setListener(object : Animator.AnimatorListener {
//
//                override fun onAnimationEnd(animation: Animator) {
//                    onesCurrentValue.text = String.format(
//                        Locale.getDefault(),
//                        "%d",
//                        newValue
//                    )
//                    onesCurrentValue.translationY = 0f
//                }
//                override fun onAnimationStart(animation: Animator) {}
//                override fun onAnimationCancel(animation: Animator) {}
//                override fun onAnimationRepeat(animation: Animator) {}
//            }).start()
//    }
}