package com.dariushm2.bottomsheet.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class NavConfig : Parcelable

@Parcelize
data class FirstFragmentConfig(
    val title: String
) : NavConfig()

@Parcelize
data class SecondFragmentConfig(
    val id: Int
) : NavConfig()

@Parcelize
data class ThirdFragmentConfig(
    val title: String
) : NavConfig()

@Parcelize
data class HomeFragmentConfig(
    val title: String
) : NavConfig()

@Parcelize
data class MyBetsFragmentConfig(
    val title: String
) : NavConfig()

@Parcelize
data class LiveFragmentConfig(
    val title: String
) : NavConfig()