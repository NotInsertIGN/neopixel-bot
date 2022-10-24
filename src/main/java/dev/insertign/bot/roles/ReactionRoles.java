package dev.insertign.bot.roles;

import dev.insertign.bot.NeopixelBot;
import dev.insertign.bot.util.Roles;
import dev.insertign.bot.util.UserUtil;
import org.javacord.api.entity.user.User;

public class ReactionRoles {
    public static void registerReactionAddEvent() {
        NeopixelBot.ROLES_CHANNEL.addReactionAddListener(event -> {
            User user = event.requestUser().join();
            String unicode = event.getEmoji().asUnicodeEmoji().orElseThrow();
            if(unicode.equals("⚪") && !UserUtil.isContributor(user)) {
                user.addRole(Roles.GUEST);
            } else if(unicode.equals("\uD83D\uDFE2")) {
                user.addRole(Roles.CONTRIBUTOR);
            } else if(unicode.equals("\uD83D\uDD35") && UserUtil.isContributor(user)) {
                user.addRole(Roles.PYTHON);
            } else if(unicode.equals("\uD83D\uDFE0") && UserUtil.isContributor(user)) {
                user.addRole(Roles.JAVA);
            } else if(unicode.equals("\uD83D\uDFE1") && UserUtil.isContributor(user)) {
                user.addRole(Roles.JAVASCRIPT);
            } else if(unicode.equals("\uD83D\uDD34") && UserUtil.isContributor(user)) {
                user.addRole(Roles.RUST);
            } else if(unicode.equals("\uD83D\uDFE3") && UserUtil.isContributor(user)) {
                user.addRole(Roles.PR);
            }
        });
    }
    public static void registerReactionRemoveEvent() {
        NeopixelBot.ROLES_CHANNEL.addReactionRemoveListener(event -> {
            User user = event.requestUser().join();
            String unicode = event.getEmoji().asUnicodeEmoji().orElseThrow();
            if(unicode.equals("⚪") && UserUtil.isGuest(user)) {
                user.removeRole(Roles.GUEST);
            } else if(unicode.equals("\uD83D\uDFE2") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.CONTRIBUTOR);
                user.removeRole(Roles.PYTHON);
                user.removeRole(Roles.JAVA);
                user.removeRole(Roles.JAVASCRIPT);
                user.removeRole(Roles.RUST);
                user.removeRole(Roles.PR);
            } else if(unicode.equals("\uD83D\uDD35") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.PYTHON);
            } else if(unicode.equals("\uD83D\uDFE0") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.JAVA);
            } else if(unicode.equals("\uD83D\uDFE1") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.JAVASCRIPT);
            } else if(unicode.equals("\uD83D\uDD34") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.RUST);
            } else if(unicode.equals("\uD83D\uDFE3") && UserUtil.isContributor(user)) {
                user.removeRole(Roles.PR);
            }
        });
    }
    public static void registerEvents() {
        registerReactionAddEvent();
        registerReactionRemoveEvent();
    }
}
