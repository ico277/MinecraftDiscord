package xyz.ico277.MinecraftDiscord.Minecraft.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.ico277.MinecraftDiscord.Discord.DiscordMain;

import java.awt.*;

public class onMessage implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        DiscordMain bot = DiscordMain.getInstance();
        if (bot != null) {
            try {
                TextChannel DiscordChannel = bot.getJDA().getTextChannelById(bot.getChannelID());
                if (DiscordChannel != null) {
                    MessageEmbed embed = new EmbedBuilder()
                            .setTitle("**" + player.getName() + "** sent a message")
                            .setDescription(message)
                            .setColor(Color.CYAN)
                            .build();
                    DiscordChannel.sendMessage(embed).queue();
                } else {
                    Bukkit.getLogger().warning( ChatColor.RED + "The ChannelID hasn't been set up properly!");
                }
            } catch (Exception ignored) {
                Bukkit.getLogger().warning( ChatColor.RED + "The ChannelID hasn't been set up properly!");
            }
        }
    }

}
