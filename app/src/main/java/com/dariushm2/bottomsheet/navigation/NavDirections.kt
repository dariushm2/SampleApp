package com.dariushm2.bottomsheet.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.dariushm2.bottomsheet.*
import org.koin.core.component.KoinComponent

interface DestinationCreator {

    fun canHandle(deeplink: Deeplink): Boolean

    fun destination(deeplink: Deeplink): NavDirections

    fun navConfig(extras: BaseDeeplinkExtras): NavConfig?
}

object DestinationFactory : KoinComponent {

    private val deeplinkCreators by lazy { getKoin().getAll<DestinationCreator>() }

    fun findCreator(deeplink: Deeplink): DestinationCreator? {
        Log.e("nav", deeplinkCreators.toString())
        for (deeplinkCreator in deeplinkCreators) {
            if (deeplinkCreator.canHandle(deeplink))
                return deeplinkCreator
        }
        return null
    }
}

fun NavController.navigate(deeplink: Deeplink) {
    val destinationCreator = DestinationFactory.findCreator(deeplink)
    destinationCreator?.destination(deeplink)?.let { navigate(it) }
}

class FirstFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.Register.First

    override fun destination(deeplink: Deeplink): NavDirections =
        ThirdFragmentDirections.toFirstFragment(
            deeplink.extras?.let {
            navConfig(it)
        } as FirstFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.Register.First).let {
            return FirstFragmentConfig(
                title = it.name
            )
        }
        return null
    }
}

class SecondFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.Register.Second

    override fun destination(deeplink: Deeplink): NavDirections =
        FirstFragmentDirections.actionFirstFragmentToSecondFragment(
//            deeplink.extras?.let {
//            navConfig(it)
//        } as SecondFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.Register.Second).let {
            return SecondFragmentConfig(
                id = it.id
            )
        }
        return null
    }
}

class ThirdFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.Register.Third

    override fun destination(deeplink: Deeplink): NavDirections =
        ThirdFragmentDirections.toThirdFragment(
            deeplink.extras?.let {
            navConfig(it)
        } as ThirdFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.Register.Third).let {
            return ThirdFragmentConfig(
                title = it.title
            )
        }
        return null
    }
}

class HomeFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.MainTabs.Home

    override fun destination(deeplink: Deeplink): NavDirections =
        HomeFragmentDirections.toHomeFragment(
            deeplink.extras?.let {
                navConfig(it)
            } as HomeFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.MainTabs.Home).let {
            return HomeFragmentConfig(
                title = it.title
            )
        }
        return null
    }
}

class MyBetsFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.MainTabs.MyBets

    override fun destination(deeplink: Deeplink): NavDirections =
        MyBetsFragmentDirections.toMyBetsFragment(
            deeplink.extras?.let {
                navConfig(it)
            } as MyBetsFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.MainTabs.MyBets).let {
            return MyBetsFragmentConfig(
                title = it.title
            )
        }
        return null
    }
}

class LiveFragmentCreator : DestinationCreator {

    override fun canHandle(deeplink: Deeplink) =
        deeplink.extras is DeeplinkExtras.MainTabs.Live

    override fun destination(deeplink: Deeplink): NavDirections =
        LiveFragmentDirections.toLiveFragment(
            deeplink.extras?.let {
                navConfig(it)
            } as LiveFragmentConfig
        )

    override fun navConfig(extras: BaseDeeplinkExtras): NavConfig? {
        (extras as DeeplinkExtras.MainTabs.Live).let {
            return LiveFragmentConfig(
                title = it.title
            )
        }
        return null
    }
}

