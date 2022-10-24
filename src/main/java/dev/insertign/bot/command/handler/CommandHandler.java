package dev.insertign.bot.command.handler;

import dev.insertign.bot.NeopixelBot;
import dev.insertign.bot.command.commands.PullRequestCommand;
import dev.insertign.bot.util.Colors;
import dev.insertign.bot.util.Roles;
import dev.insertign.bot.util.SlashCommandCleanup;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;

import java.util.List;
import java.util.Optional;

public class CommandHandler {
    public static void registerPullRequestCommand() {
        PullRequestCommand.register();
        NeopixelBot.API.addSlashCommandCreateListener(event -> {
            NeopixelBot.THREAD_POOL.submit(() -> {
                if(!event.getSlashCommandInteraction().getUser().getRoles(NeopixelBot.SERVER).contains(Roles.CONTRIBUTOR)) {
                    event.getSlashCommandInteraction()
                            .createImmediateResponder()
                            .setContent("Uh, uh uh! You didn't say the magic word!" +
                                    "\n\n" +
                                    "You don't have the required role to use this command!").respond().join();
                } else {
                    final long TIMESTAMP = event.getInteraction().getCreationTimestamp().toEpochMilli() / 1000;
                    final SlashCommandInteraction INTERACTION = event.getSlashCommandInteraction();
                    final String COMMAND_NAME = "Pull Request";
                    final User USER = INTERACTION.getUser();
                    final String AUTHOR_NAME = USER.getName() + "#" + USER.getDiscriminator();
                    final SlashCommandCleanup cleanup = new SlashCommandCleanup(COMMAND_NAME, INTERACTION, AUTHOR_NAME);
                    if (INTERACTION.getCommandName().equals("pr")) {
                        if (INTERACTION.getOptions().get(0).getName().equals("request")) {
                            SlashCommandInteractionOption options = INTERACTION.getOptions().get(0).getOptions().get(0);
                            List<String> innerOptions = options.getOptions()
                                    .stream()
                                    .map(SlashCommandInteractionOption::getStringValue)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get).toList();
                            final String NAME = innerOptions.get(0);
                            final String DESCRIPTION = innerOptions.get(1);
                            final String LINK = innerOptions.get(2);
                            final String INFO;
                            String TEMP;
                            try {
                                TEMP = innerOptions.get(3);
                            } catch (Exception e) {
                                TEMP = "Nothing provided.";
                            }
                            INFO = TEMP;
                            if (options.getName().equals("review")) {
                                NeopixelBot.PR_CHANNEL.sendMessage(
                                        new EmbedBuilder()
                                                .setAuthor("Request started by " + AUTHOR_NAME, null, NeopixelBot.ICON_URL)
                                                .setTitle(NAME)
                                                .setColor(Colors.PR_REVIEW_COLOR)
                                                .setDescription(DESCRIPTION)
                                                .addInlineField("Time created", "<t:" + TIMESTAMP + ":R>")
                                                .addInlineField("Link", "" + LINK + "")
                                                .addInlineField("Ping", "<@&"+ NeopixelBot.PR_REQUEST_ROLE.getIdAsString() + ">")
                                                .addField("Additional Info", INFO)
                                                .setFooter(Colors.FOOTER_TEXT + " • Pull Request")
                                );
                                cleanup.cleanup();
                            } else if (options.getName().equals("close")) {
                                NeopixelBot.PR_CHANNEL.sendMessage(
                                        new EmbedBuilder()
                                                .setAuthor("Request started by " + AUTHOR_NAME, null, NeopixelBot.ICON_URL)
                                                .setTitle(NAME)
                                                .setColor(Colors.PR_CLOSE_COLOR)
                                                .setDescription(DESCRIPTION)
                                                .addInlineField("Time created", "<t:" + TIMESTAMP + ":R>")
                                                .addInlineField("Link", "" + LINK + "")
                                                .addInlineField("Ping", "<@&" + NeopixelBot.PR_REQUEST_ROLE.getIdAsString() + ">")
                                                .addField("Additional Info", INFO)
                                                .setFooter(Colors.FOOTER_TEXT + " • Pull Request")
                                );
                                cleanup.cleanup();
                            }
                        }
                    }
                }
            });
        });
    }
}
