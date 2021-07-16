package bet.thescore.android.ui.customview

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.dariushm2.bottomsheet.NameAdapter

class SlideRightItemAnimator : DefaultItemAnimator() {

    companion object {
        private const val ZERO = 0F
        private const val HUNDRED = 100F
        const val DAMPING_RATIO = 0.9F
        const val STIFFNESS = 100F
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {

        //holder.itemView.translationX -= holder.itemView.width
        
        Handler().postDelayed({
            holder.itemView.playSlideRightAnim(holder)
        }, 1000L)

        return super.animateAdd(holder)
    }

    private fun View.playSlideRightAnim(holder: RecyclerView.ViewHolder) {
        SpringAnimation(this, ParamSlideProp(), ZERO).apply {
            spring.stiffness = STIFFNESS
            spring.dampingRatio = DAMPING_RATIO
            addEndListener { animation, canceled, value, velocity ->
                dispatchAnimationFinished(holder)
            }
            start()
        }
    }

    private inner class ParamSlideProp : FloatPropertyCompat<View>("ParamSlide") {

        override fun setValue(view: View, animValue: Float) {
            view.translationX = animValue
        }

        override fun getValue(view: View): Float = view.translationX - view.width
    }
}