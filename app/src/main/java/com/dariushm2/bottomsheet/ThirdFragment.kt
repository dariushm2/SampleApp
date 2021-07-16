package com.dariushm2.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dariushm2.bottomsheet.databinding.FragmentThirdBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras
import com.dariushm2.bottomsheet.navigation.navigate
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ThirdFragment : Fragment() {

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

            val deeplink = Deeplink("first", DeeplinkExtras.Register.First("Dariush"))

            refresh.setOnClickListener {
                findNavController().navigate(deeplink)

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

    fun CoroutineScope.postDelayedd(initialDelay: Long, op: () -> Unit) = (0..0).asFlow().onEach {
        delay(initialDelay)
        op.invoke()
    }.launchIn(this)

    fun CoroutineScope.postDelayed(initialDelay: Long, op: () -> Unit) = this.launch {
        delay(initialDelay)
        op.invoke()
    }
}