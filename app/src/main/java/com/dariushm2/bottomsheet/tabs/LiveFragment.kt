package com.dariushm2.bottomsheet.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dariushm2.bottomsheet.BaseFragment
import com.dariushm2.bottomsheet.databinding.FragmentLiveBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras

class LiveFragment : BaseFragment() {

    private lateinit var binding: FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveBinding.inflate(inflater)

        val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("live"))

        Log.e("nav", "Live onCreateView $this")

        binding.btnGo.setOnClickListener {
            navigate(deeplink)
        }

        return binding.root
    }
}