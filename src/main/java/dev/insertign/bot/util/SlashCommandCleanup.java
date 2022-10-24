package dev.insertign.bot.util;

import dev.insertign.bot.NeopixelBot;
import org.javacord.api.interaction.SlashCommandInteraction;

public class SlashCommandCleanup {
    private final String command;
    private final SlashCommandInteraction interaction;
    private final Object author;

    public SlashCommandCleanup(String command, SlashCommandInteraction interaction, String author) {
        this.command = command;
        this.interaction = interaction;
        this.author = author;
    }

    public void cleanup() {
        interaction.createImmediateResponder().setContent("Command executed successfully").respond().join();
        NeopixelBot.LOGGER.info(command + " called by " + author);
    }

    public void cleanup(String response) {
        interaction.createImmediateResponder().setContent(response).respond().join();
        NeopixelBot.LOGGER.info(command + " called by " + author);
    }

}
