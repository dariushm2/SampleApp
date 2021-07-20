package com.dariushm2.bottomsheet

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.navigate

open class BaseFragment : Fragment() {

    fun navigate(deeplink: Deeplink) {
        findNavController().navigate(deeplink)
    }
}