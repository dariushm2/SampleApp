package com.dariushm2.bottomsheet.circle

data class ChipData(
    val name: String,
    val isLive: Boolean = false,
    val stableId: Long = name.hashCode().toLong() 
)