package com.dariushm2.bottomsheet.navigation

import androidx.navigation.NavArgs

interface NavComponent<Args : NavArgs, Config : NavConfig> {
    val navArgs: Args
    val navConfig: Config?

    fun NavComponent<Args, Config>.navConfig(config: () -> Config): Lazy<Config> = lazy { config() }
}