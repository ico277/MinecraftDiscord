package xyz.ico277.MinecraftDiscord.Minecraft;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.ico277.MinecraftDiscord.Discord.DiscordMain;
import xyz.ico277.MinecraftDiscord.Minecraft.events.onMessage;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class MinecraftMain extends JavaPlugin {

    private final File ConfigFile = new File("plugins/MinecraftDiscord/config.yml");
    private final YamlConfiguration config = new YamlConfiguration();
    private DiscordMain DiscordBot;

    @Override
    public void onEnable() {
        //config files
        config.addDefault("bot.token", "token here");
        config.addDefault("bot.channelID", "message channel ID here");
        config.addDefault("bot.prefix", "!");
        Logger logger = Bukkit.getLogger();
        try {
            logger.info("Loading config file...");
            File ConfigDir = new File("plugins/MinecraftDiscord/");
            if (!ConfigDir.exists())  {
                ConfigDir.mkdir();
            }
            if (ConfigFile.exists()) {
                config.load(ConfigFile);
                logger.info("Successfully loaded config!");
            } else {
                ConfigFile.createNewFile();
                OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(ConfigFile));
                InputStreamReader inputStream = new InputStreamReader(this.getResource("config.yml"));
                Scanner s = new Scanner(inputStream);
                while(s.hasNextLine()) {
                    outputStream.write(s.nextLine());
                }
                logger.warning("No config file found! Created one for you. Please edit the config!");
                outputStream.close();
                this.setEnabled(false);
            }
        } catch (IOException ex) {
            logger.warning(ChatColor.RED + "There was an I/O error whilst trying to load the config!");
            ex.printStackTrace();
            this.setEnabled(false);
        } catch (InvalidConfigurationException ex) {
            logger.warning(ChatColor.RED + "There was an error whilst trying to load the config!");
            ex.printStackTrace();
            this.setEnabled(false);
        }

        //Start discord bot
        if (this.isEnabled()) {
            try {
                this.DiscordBot = new DiscordMain(config.getString("bot.token"), config.getString("bot.prefix"), config.getString("bot.channelID"));
                DiscordBot.Start();
            } catch (LoginException e) {
                logger.warning(ChatColor.RED + "Critical error: There was an error whilst starting the bot! Maybe the token is invalid?");
                e.printStackTrace();
                this.setEnabled(false);
                return;
            }
            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new onMessage(), this);
        }
    }

    @Override
    public void onDisable() {
        if (this.DiscordBot != null)
            this.DiscordBot.getJDA().shutdownNow();
    }

}
