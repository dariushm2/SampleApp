package com.dariushm2.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager

class MyViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec1: Int) {
        var heightMeasureSpec = heightMeasureSpec1
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("bottom", "onMeasure")
        var height = 0
        val childWidthSpec = MeasureSpec.makeMeasureSpec(
            0.coerceAtLeast(
                MeasureSpec.getSize(widthMeasureSpec) -
                        paddingLeft - paddingRight
            ),
            MeasureSpec.getMode(widthMeasureSpec)
        )
        for (i in 0 until childCount) {
            val child: View = getChildAt(i)
            child.measure(childWidthSpec, MeasureSpec.UNSPECIFIED)
            val h: Int = child.measuredHeight
            if (h > height) height = h
        }
        if (height != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}