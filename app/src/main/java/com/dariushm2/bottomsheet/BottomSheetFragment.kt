package com.dariushm2.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.dariushm2.bottomsheet.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetLayoutBinding
    private lateinit var dialog: BottomSheetDialog

    private inner class MyPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<Fragment>
    ) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLayoutBinding.inflate(inflater)

        val fragments = listOf(
            SecondFragment.newInstance(),
            SecondFragment.newInstance(),
            SecondFragment.newInstance()
        )

        binding.viewPager.offscreenPageLimit = 2

        val pagerAdapter = MyPagerAdapter(childFragmentManager, fragments)

        binding.viewPager.adapter = pagerAdapter

        binding.btnYes.setOnClickListener {
            if (binding.txtWarning.isGone)
                binding.txtWarning.visibility = View.VISIBLE
            else
                binding.txtWarning.visibility = View.GONE
            setBottomMargin()
        }

        return binding.root
    }

    private fun setBottomMargin() {
        val coordinator =
            dialog.findViewById<CoordinatorLayout>(com.google.android.material.R.id.coordinator)
        val containerLayout: FrameLayout =
            dialog.findViewById<FrameLayout>(com.google.android.material.R.id.container) as FrameLayout
        val footer = dialog.findViewById<LinearLayout>(R.id.bottomLayout)
        footer?.post {
            (coordinator?.layoutParams as ViewGroup.MarginLayoutParams).apply {
                footer.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                this.bottomMargin = footer.measuredHeight
                containerLayout.requestLayout()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            dialog = it as BottomSheetDialog

            val containerLayout: FrameLayout =
                dialog.findViewById<FrameLayout>(com.google.android.material.R.id.container) as FrameLayout
            val button = FrameLayout(requireContext())
            val bottomLayout = binding.root.findViewById<LinearLayout>(R.id.bottomLayout)
            val parent = bottomLayout.parent as ViewGroup?
            parent?.removeView(bottomLayout)
            button.addView(bottomLayout)

            button.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.BOTTOM
            }
            containerLayout.addView(button, containerLayout.childCount)

            setBottomMargin()
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }
}