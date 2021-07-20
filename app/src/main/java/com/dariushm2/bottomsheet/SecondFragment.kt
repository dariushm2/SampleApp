package com.dariushm2.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dariushm2.bottomsheet.circle.ChipData
import com.dariushm2.bottomsheet.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private var data = emptyList<ChipData>()
    private lateinit var adapter: NameAdapter
    private var isLiveAdded = false

    companion object {
        fun newInstance() = SecondFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)

        with(binding) {

            //recyclerView.itemAnimator = SlideRightItemAnimator()


            data = getInitialData()

            adapter = NameAdapter(requireContext(), data)

            //binding.recyclerView.recycledViewPool.setMaxRecycledViews(0, 0)


            recyclerView.adapter = adapter
            recyclerView.post {
                //recyclerView.scrollToPosition(adapter.itemCount - 1)
            }

            btnRefresh.setOnClickListener {

                //data = getSecondData()
                //adapter.notifyDataSetChanged()
                //Log.e("anim", adapter.itemCount.toString())
                //recyclerView.post {
                //    adapter.notifyDataSetChanged()
                //}
            }
        }

        return binding.root
    }

    fun getInitialData() = listOf(
        ChipData("1"),
        ChipData("2"),
        ChipData("3"),
        ChipData("4"),
        ChipData("5")
    )

    fun getSecondData() = listOf(
        ChipData("0"),
        ChipData("1"),
        ChipData("2"),
        ChipData("3"),
        ChipData("4"),
        ChipData("5")
    )
}