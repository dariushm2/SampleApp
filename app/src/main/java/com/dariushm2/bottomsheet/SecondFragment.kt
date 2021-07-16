package com.dariushm2.bottomsheet

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import bet.thescore.android.ui.customview.SlideRightItemAnimator
import com.dariushm2.bottomsheet.databinding.FragmentSecondBinding
import kotlinx.coroutines.*
import java.lang.Error

class SecondFragment private constructor(): Fragment() {

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

                val url = "https://thescore.app.link?account=theScore&utm_source=theScore%20Bet&utm_campaign=Stay%20up%20to%20date%20with%20theScore&utm_medium=Event%20alerts%20modal&tags=betting_integration,modal,alerts,android"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(FLAG_ACTIVITY_NEW_TASK)

                }
                startActivity(intent)

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