package dev.insertign.bot.util;

import dev.insertign.bot.NeopixelBot;
import org.javacord.api.entity.user.User;

public class UserUtil {
    public static boolean isGuest(User user) {
        return NeopixelBot.SERVER.getRoles(user).contains(Roles.GUEST);
    }
    public static boolean isContributor(User user) {
        return NeopixelBot.SERVER.getRoles(user).contains(Roles.CONTRIBUTOR);
    }
}
