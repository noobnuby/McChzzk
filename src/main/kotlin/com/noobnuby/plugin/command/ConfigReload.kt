package com.noobnuby.plugin.command

import com.noobnuby.plugin.Main
import com.noobnuby.plugin.chat
import com.noobnuby.plugin.chzzk
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import javax.inject.Named

class ConfigReload:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender.isOp) {
            Main.INSTANCE.reloadConfig()
            val channelId = Main.INSTANCE.config.getString("channelId")
            val channel = chzzk.getChannel(channelId)
            sender.sendMessage(Component.text("config 파일을 리로드 하였습니다.", NamedTextColor.GREEN))
            sender.sendMessage(Component.text("현재 스트리머 : ", NamedTextColor.GREEN).append(Component.text("${channel.channelName}",NamedTextColor.YELLOW)))
            chat.close()
            Main.INSTANCE.onEnable()
            return true
        }
        return false
    }
}