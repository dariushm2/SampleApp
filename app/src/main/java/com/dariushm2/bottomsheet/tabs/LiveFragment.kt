package com.dariushm2.bottomsheet.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dariushm2.bottomsheet.databinding.FragmentLiveBinding
import com.dariushm2.bottomsheet.navigation.navigate
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras

class LiveFragment : Fragment() {

    private lateinit var binding: FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveBinding.inflate(inflater)

        val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("live"))

        Log.e("nav", "Live onCreateView $this")

        binding.btnGo.setOnClickListener {
            findNavController().navigate(deeplink)
        }

        return binding.root
    }
}