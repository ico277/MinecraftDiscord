package xyz.ico277.MinecraftDiscord.Discord.events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class onReady extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent e) {
        Bukkit.getLogger().info("Discord bot started as " + e.getJDA().getSelfUser().getAsTag());

        //TODO add Commands
        e.getJDA().addEventListener(new onMessage());
    }

}
