package com.dariushm2.bottomsheet.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.dariushm2.bottomsheet.databinding.FragmentHomeBinding
import androidx.navigation.fragment.navArgs
import com.dariushm2.bottomsheet.BaseFragment
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras
import com.dariushm2.bottomsheet.navigation.HomeFragmentConfig
import com.dariushm2.bottomsheet.navigation.NavComponent

class HomeFragment : BaseFragment(), NavComponent<HomeFragmentArgs, HomeFragmentConfig> {

    private lateinit var binding: FragmentHomeBinding
    private var isViewCreated = false

    private val viewModel: HomeViewModel by viewModels()

    override val navArgs: HomeFragmentArgs by navArgs()
    override val navConfig: HomeFragmentConfig by navConfig {
        navArgs.config ?: HomeFragmentConfig(navArgs.tabType.toString())
        navArgs.config ?: HomeFragmentConfig(navArgs.tabType.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        if (isViewCreated) return binding.root

        binding = FragmentHomeBinding.inflate(inflater)

        val deeplink = Deeplink("register", DeeplinkExtras.Register.Third("Home"))

        binding.tabName.text = "Home ${navConfig.title}"

        Log.e("nav", "Home onCreateView $this")

        binding.btnGo.setOnClickListener {
            navigate(deeplink)
        }
        isViewCreated = true
        return binding.root
    }
}