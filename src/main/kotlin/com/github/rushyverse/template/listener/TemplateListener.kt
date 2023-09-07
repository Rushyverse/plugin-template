package com.github.rushyverse.template.listener

import com.github.rushyverse.api.Plugin
import com.github.rushyverse.api.koin.inject
import com.github.rushyverse.api.player.ClientManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import kotlin.time.Duration.Companion.seconds

/**
 * Example listener.
 * @property clientManager Client manager to retrieve custom player data.
 */
class TemplateListener(plugin: Plugin) : Listener {

    private val clientManager: ClientManager by inject(plugin.id)

    /**
     * Called when a player joins the server.
     * @param event The event.
     */
    @EventHandler
    @Suppress("unused")
    suspend fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendMessage("Welcome to the server!")

        val client = clientManager.getClient(player)
        client.launch {
            delay(10.seconds)
            player.sendMessage("You've been on the server for 10 seconds!")
        }
    }
}
