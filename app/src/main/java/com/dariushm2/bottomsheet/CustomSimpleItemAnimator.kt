package com.dariushm2.bottomsheet

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

class CustomSimpleItemAnimator : SimpleItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        Log.e("anim", "animateChange")
        return false
    }

    override fun runPendingAnimations() {
        Log.e("anim", "")
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        Log.e("anim", "")
    }

    override fun endAnimations() {
        Log.e("anim", "")
    }

    override fun isRunning(): Boolean {
        Log.e("anim", "")
        return false
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        Log.e("anim", "animateRemove")
        return false
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        Log.e("anim", "animateAdd")
        return false
    }

    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        Log.e("anim", "animateMove")
        return false
    }
}