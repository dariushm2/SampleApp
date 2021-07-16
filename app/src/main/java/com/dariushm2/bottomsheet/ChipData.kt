package com.dariushm2.bottomsheet

data class ChipData(
    val name: String,
    val isLive: Boolean = false,
    val stableId: Long = name.hashCode().toLong() 
)