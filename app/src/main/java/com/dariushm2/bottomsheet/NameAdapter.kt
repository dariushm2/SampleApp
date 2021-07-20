package com.dariushm2.bottomsheet

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.recyclerview.widget.RecyclerView
import com.dariushm2.bottomsheet.circle.ChipData
import com.dariushm2.bottomsheet.circle.Circle
import com.dariushm2.bottomsheet.circle.CircleAnimation
import com.dariushm2.bottomsheet.databinding.ItemListHorizontalBinding


class NameAdapter(
    private val context: Context,
    private val data: List<ChipData>
) : RecyclerView.Adapter<NameAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private lateinit var binding: ItemListHorizontalBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListHorizontalBinding.inflate(mInflater)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NameAdapter.ViewHolder, position: Int) {
        holder.txtName.text = data[position].name
        if (position == 0) {
            Handler(Looper.getMainLooper()).postDelayed({
                //holder.circle.anim()
            }, 1000L)
        }

        val animation = CircleAnimation(holder.circle)
        animation.startOffset
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.container)
        val txtName: TextView = itemView.findViewById(R.id.text)
        val circle: Circle = itemView.findViewById(R.id.circle)
    }

    private fun playAlphaAnim() = with(binding.container) {


        SpringAnimation(
            binding.container,
            ParamSlideProp(),
            100F
        ).apply {
            alpha = 0F
            start()
        }
    }

    private inner class ParamSlideProp : FloatPropertyCompat<View>("ParamSlide") {

        override fun setValue(view: View, animValue: Float) {
            view.alpha = animValue
        }

        override fun getValue(view: View): Float = view.alpha
    }
}