package com.dariushm2.bottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dariushm2.bottomsheet.databinding.FragmentHomeBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras
import com.dariushm2.bottomsheet.navigation.navigate

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("Home"))

        binding.btnGo.setOnClickListener {
            findNavController().navigate(deeplink)
        }


        return binding.root
    }
}