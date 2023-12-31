package com.noobnuby.plugin.events

import com.noobnuby.plugin.Main
import com.noobnuby.plugin.chat
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import xyz.r2turntrue.chzzk4j.chat.ChatEventListener
import xyz.r2turntrue.chzzk4j.chat.ChatMessage

class ChzzkChatListener(val plugin:Main): ChatEventListener {
    override fun onConnect() {
        plugin.server.logger.info("채팅에 연결되었습니다.")
        chat.requestRecentChat(50)
    }

    override fun onChat(msg: ChatMessage) {
        plugin.server.scheduler.runTask(plugin, Runnable {
            val chzzkMsg = Component.text("[",NamedTextColor.YELLOW).append(Component.text("치지직",NamedTextColor.GREEN).append(Component.text("]",NamedTextColor.YELLOW).append(Component.text(" ${msg.profile.nickname}",NamedTextColor.GREEN).append(Component.text(" : ${msg.content}", NamedTextColor.WHITE)))))
            Bukkit.broadcast(chzzkMsg)
        })
    }
}