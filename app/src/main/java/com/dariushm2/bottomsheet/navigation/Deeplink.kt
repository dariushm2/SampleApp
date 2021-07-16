package com.dariushm2.bottomsheet.navigation

import java.io.Serializable

interface BaseDeeplinkExtras : Serializable

class Deeplink(
    val url: String? = null,
    val extras: BaseDeeplinkExtras? = null
)

sealed class DeeplinkExtras : BaseDeeplinkExtras {
    sealed class Register : DeeplinkExtras() {
        data class First(
            val name: String
        ) : Register()

        data class Second(
            val id: Int
        ) : Register()

        data class Third(
            val title: String
        ) : Register()
    }

    sealed class MainTabs : BaseDeeplinkExtras {
        data class Home(
            val title: String
        ) : MainTabs()

        data class MyBets(
            val title: String
        ) : MainTabs()

        data class Live(
            val title: String
        ) : MainTabs()
    }
}

