package com.dariushm2.bottomsheet.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dariushm2.bottomsheet.databinding.FragmentHomeBinding
import androidx.navigation.fragment.navArgs
import com.dariushm2.bottomsheet.navigation.*

class HomeFragment : Fragment(), NavComponent<HomeFragmentArgs, HomeFragmentConfig> {

    private lateinit var binding: FragmentHomeBinding

    override val navArgs: HomeFragmentArgs by navArgs()
    override val navConfig: HomeFragmentConfig by navConfig {
        navArgs.config ?: HomeFragmentConfig(navArgs.tabType.toString())
        navArgs.config ?: HomeFragmentConfig(navArgs.tabType.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("Home"))

        binding.tabName.text = "Home ${navConfig.title}"

        Log.e("nav", "Home onCreateView $this")

        binding.btnGo.setOnClickListener {
            findNavController()?.navigate(deeplink)
        }


        return binding.root
    }
}