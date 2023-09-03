package com.github.rushyverse.template

import com.github.rushyverse.api.Plugin
import com.github.rushyverse.api.extension.registerListener
import com.github.rushyverse.api.player.Client
import com.github.rushyverse.api.translation.ResourceBundleTranslator
import com.github.rushyverse.api.translation.registerResourceBundleForSupportedLocales
import com.github.rushyverse.template.craft.registerUltimateSwordCraft
import com.github.rushyverse.template.listener.TemplateListener
import com.github.shynixn.mccoroutine.bukkit.launch
import com.github.shynixn.mccoroutine.bukkit.scope
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlinx.coroutines.plus
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import java.util.*

/**
 * Plugin of the project.
 */
@Suppress("unused")
class TemplatePlugin : Plugin("idTemplate", "bundleTemplate") {

    override suspend fun onEnableAsync() {
        super.onEnableAsync()
        modulePlugin<TemplatePlugin>()
        registerCrafts()

        registerListener { TemplateListener(this) }

        commandTree("template") {
            playerExecutor { player, _ ->
                this@TemplatePlugin.launch {
                    val locale = clientManager.getClient(player).lang().locale
                    val translation1 = translator.get("example_word_1", locale)
                    val translation2 = translator.get("example_word_2", locale)

                    player.sendMessage(Component.text("$translation1 $translation2", NamedTextColor.GREEN))
                }
            }
        }
    }

    override fun createTranslator(): ResourceBundleTranslator {
        return super.createTranslator().apply {
            registerResourceBundleForSupportedLocales(bundle, ResourceBundle::getBundle)
        }
    }

    override fun createClient(player: Player): Client {
        return Client(
            player.uniqueId,
            scope + SupervisorJob(scope.coroutineContext.job)
        )
    }

    private fun registerCrafts() {
        registerUltimateSwordCraft()
    }
}
