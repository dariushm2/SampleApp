package com.dariushm2.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dariushm2.bottomsheet.databinding.FragmentThirdBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras

class ThirdFragment : BaseFragment() {

    private var binding: FragmentThirdBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater)

        with(binding!!) {

            stiffness.minValue = 0
            stiffness.maxValue = 10_000
            stiffness.value = 900

            dampingRatio.minValue = 0
            dampingRatio.maxValue = 10_000
            dampingRatio.value = 65

            val deeplink = Deeplink("first", DeeplinkExtras.Register.Second(2))

            refresh.setOnClickListener {
                navigate(deeplink)

                //circle.reset(R.color.transparent)
//                lifecycleScope.postDelayed(2000) {
//                    circle.anim(stiffness.value.toFloat(), dampingRatio.value.div(10).toFloat())
//                }
            }
        }

        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("delay", "onDestroy")
        binding = null
    }
}