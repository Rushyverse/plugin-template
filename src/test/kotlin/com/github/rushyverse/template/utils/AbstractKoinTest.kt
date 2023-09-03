package com.github.rushyverse.template.utils

import com.github.rushyverse.api.APIPlugin
import com.github.rushyverse.api.Plugin
import com.github.rushyverse.api.koin.CraftContext
import com.github.rushyverse.api.koin.loadModule
import io.mockk.every
import io.mockk.mockk
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

open class AbstractKoinTest {

    lateinit var plugin: Plugin

    private lateinit var pluginId: String

    @BeforeTest
    open fun onBefore() {
        pluginId = randomString()
        CraftContext.startKoin(pluginId) { }
        CraftContext.startKoin(APIPlugin.ID_API) { }

        loadPluginModule {
            plugin = mockk {
                every { id } returns pluginId
                every { name } returns randomString()
            }
            single { plugin }
        }
    }

    @AfterTest
    open fun onAfter() {
        CraftContext.stopKoin(pluginId)
        CraftContext.stopKoin(APIPlugin.ID_API)
    }

    fun loadPluginModule(moduleDeclaration: ModuleDeclaration): Module =
        loadModule(pluginId, moduleDeclaration = moduleDeclaration)

    fun loadApiPluginModule(moduleDeclaration: ModuleDeclaration): Module =
        loadModule(APIPlugin.ID_API, moduleDeclaration = moduleDeclaration)

}
