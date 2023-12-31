package com.noobnuby.plugin

import com.noobnuby.plugin.command.ConfigReload
import com.noobnuby.plugin.events.ChzzkChatListener
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin
import xyz.r2turntrue.chzzk4j.Chzzk
import xyz.r2turntrue.chzzk4j.ChzzkBuilder
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat

lateinit var chzzk: Chzzk
lateinit var chat: ChzzkChat

class Main : JavaPlugin() {
    companion object { lateinit var INSTANCE: Main }
    override fun onEnable() {
        INSTANCE = this
        saveDefaultConfig()
        chzzk = ChzzkBuilder().build()
        val channelId = config.getString("channelId")
        val channel = chzzk.getChannel(channelId)
        chat = chzzk.chat()
        chat.addListener(ChzzkChatListener(this))
        logger.info("Enable Plugin!")
        chat.connectFromChannelId(channelId)

        Bukkit.getPluginCommand("creload")!!.setExecutor(ConfigReload())
    }

    override fun onDisable() {
        chat.close()
    }
}