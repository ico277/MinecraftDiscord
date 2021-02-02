package xyz.ico277.MinecraftDiscord.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import xyz.ico277.MinecraftDiscord.Discord.events.onReady;

import javax.security.auth.login.LoginException;

public class DiscordMain {

    private final String Token;
    private final String Prefix;
    private final String ChannelID;
    private JDA jda = null;
    private static DiscordMain instance;

    public DiscordMain(String Token, String Prefix, String ChannelID) {
        this.Token = Token; this.Prefix = Prefix; this.ChannelID = ChannelID; instance = this;
    }

    public JDA Start() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(this.Token)
                .setActivity(Activity.playing("Created by ico277"))
                .addEventListeners(new onReady());
        this.jda = builder.build();
        return jda;
    }

    public String getToken() {
        return this.Token;
    }

    public String getPrefix() {
        return this.Prefix;
    }

    public String getChannelID() {
        return this.ChannelID;
    }

    public JDA getJDA() {
        return this.jda;
    }

    public static DiscordMain getInstance() {
        return instance;
    }

}
