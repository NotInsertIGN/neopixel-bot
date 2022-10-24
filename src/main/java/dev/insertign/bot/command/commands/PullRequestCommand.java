package dev.insertign.bot.command.commands;

import dev.insertign.bot.NeopixelBot;

import java.util.Arrays;

import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

public class PullRequestCommand {
    private static final SlashCommandBuilder COMMAND = SlashCommand.with(
            "pr", "Commands related to pull requests.",
            Arrays.asList(SlashCommandOption.createWithOptions(
                    SlashCommandOptionType.SUB_COMMAND_GROUP, "request", "Edits a channel",
                    Arrays.asList(
                            SlashCommandOption.createWithOptions(
                                    SlashCommandOptionType.SUB_COMMAND, "review", "Adds a PR review in #pull-request-review.",
                                    Arrays.asList(
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "name", "Name of the PR.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "description", "Description of the PR.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "link", "Link to the PR.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "info", "Any additional info?")
                                    )
                            ),
                            SlashCommandOption.createWithOptions(
                                    SlashCommandOptionType.SUB_COMMAND, "close", "Request pull request to be closed.",
                                    Arrays.asList(
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "name", "Name of the PR.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "reasoning", "Reasoning for why PR should be closed.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "link", "Link to the PR.", true),
                                            SlashCommandOption.create(SlashCommandOptionType.STRING, "info", "Any additional info?")
                                    )
                            )
                    )
            )));
    public static void register() {
        NeopixelBot.THREAD_POOL.submit(() -> {
            COMMAND.createGlobal(NeopixelBot.API).join();
        });
    }
}
