package dev.insertign.bot;

import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

import dev.insertign.bot.roles.ReactionRoles;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;

import dev.insertign.bot.command.handler.CommandHandler;

public class NeopixelBot {
    public static final String ID = "NeopixelBot";
    public static final String TOKEN = "<super secret token>";
    public static final Logger LOGGER = Logger.getLogger(ID);
    public static final String ICON_URL = "https://cdn.discordapp.com/attachments/1003861349425741977/1023256200961335336/neopixel.jpg";
    public static final ForkJoinPool THREAD_POOL = ForkJoinPool.commonPool();
    public static final DiscordApi API = new DiscordApiBuilder()
            .setToken(TOKEN)
            .setAllIntents()
            .login()
            .join();
    public static final Server SERVER = API.getServerById(994645819326214274L).orElseThrow();
    public static final Role PR_REQUEST_ROLE = SERVER.getRoleById(1012849076653527173L).orElseThrow();
    public static final TextChannel PR_CHANNEL = SERVER.getTextChannelById(1008094307619709098L).orElseThrow();
    public static final TextChannel ROLES_CHANNEL = SERVER.getTextChannelById(995724690104520835L).orElseThrow();

    public static void main(String[] args) {
        System.out.println(API.createBotInvite());
        CommandHandler.registerPullRequestCommand();
        ReactionRoles.registerEvents();
        LOGGER.info("Bot loaded successfully!");
    }
}
