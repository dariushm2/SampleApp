package com.dariushm2.bottomsheet.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.dariushm2.bottomsheet.navigation.navigate
import com.dariushm2.bottomsheet.databinding.FragmentMyBetsBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras

class MyBetsFragment : Fragment() {

    private lateinit var binding: FragmentMyBetsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState == null) {
            binding = FragmentMyBetsBinding.inflate(inflater)

            val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("my bets"))

            Log.e("nav", "MyBets onCreateView $this")

            binding.btnGo.setOnClickListener {
                findNavController().navigate(deeplink)
            }

        }
        return binding.root
    }
}