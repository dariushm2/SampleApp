package com.dariushm2.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dariushm2.bottomsheet.databinding.FragmentFirstBinding
import com.dariushm2.bottomsheet.navigation.FirstFragmentConfig
import com.dariushm2.bottomsheet.navigation.NavComponent
import androidx.navigation.fragment.navArgs
import com.dariushm2.bottomsheet.circle.ChipData

class FirstFragment : BaseFragment(), NavComponent<FirstFragmentArgs, FirstFragmentConfig> {

    var leftPoint = 0
    var rightPoint = 0

    private val data = mutableListOf<ChipData>()

    private lateinit var binding: FragmentFirstBinding

    override val navArgs: FirstFragmentArgs by navArgs()
    override val navConfig: FirstFragmentConfig by navConfig {
        navArgs.config ?: FirstFragmentConfig("unknown")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)

        Log.e("nav", navConfig.title)
//        val transaction = childFragmentManager.beginTransaction()
//        transaction.replace(
//            R.id.frame,
//            SecondFragment.newInstance()
//        ).commit()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val constant = 7//Math.random().times(10).toInt()

        binding.btnLeftInc.setOnClickListener {
//            leftPoint += constant
//            binding.leftTeamScoreBoard.setValue(leftPoint)
        }

        binding.btnLeftDec.setOnClickListener {
            leftPoint -= constant
            if (leftPoint < 0) leftPoint = 0
            binding.leftTeamScoreBoard.setValue(leftPoint)
        }

        binding.btnRightInc.setOnClickListener {
//            rightPoint += constant
//            binding.rightTeamScoreBoard.setValue(rightPoint)
        }

        binding.btnRightDec.setOnClickListener {
//            rightPoint -= constant
//            if (rightPoint < 0) rightPoint = 0
//            binding.rightTeamScoreBoard.setValue(rightPoint)

        }
    }
}