package xyz.ico277.MinecraftDiscord.Discord.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.ico277.MinecraftDiscord.Discord.DiscordMain;

public class onMessage extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Message message = e.getMessage();
        User Author = message.getAuthor();
        TextChannel channel = message.getTextChannel();
        String MinecraftChannelID = DiscordMain.getInstance().getChannelID();

        if (Author.isBot())
            return;

        try {
            TextChannel MinecraftChannel = e.getJDA().getTextChannelById(MinecraftChannelID);
            if (MinecraftChannel != null && MinecraftChannel.getId().equals(channel.getId())) {
                if (message.getEmbeds().size() > 0 || message.getAttachments().size() > 0) {
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "[" + ChatColor.WHITE + Author.getAsTag() + ChatColor.BLUE + "] (File/Embed) " + ChatColor.GOLD + message.getContentDisplay());
                } else {
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "[" + ChatColor.WHITE + Author.getAsTag() + ChatColor.BLUE + "] " + ChatColor.GOLD + message.getContentDisplay());
                }
            } else {
                Bukkit.getLogger().warning( ChatColor.RED + "The ChannelID hasn't been set up properly!");
            }
        } catch (Exception ignored) {
            Bukkit.getLogger().warning( ChatColor.RED + "The ChannelID hasn't been set up properly!");
        }
    }
}
