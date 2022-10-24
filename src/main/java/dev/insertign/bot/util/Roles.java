package dev.insertign.bot.util;

import dev.insertign.bot.NeopixelBot;
import org.javacord.api.entity.permission.Role;

public enum Roles {;
    public static final Role GUEST = NeopixelBot.SERVER.getRoleById(1028746189715275917L).orElseThrow();
    public static final Role CONTRIBUTOR = NeopixelBot.SERVER.getRoleById(994647929753182248L).orElseThrow();
    public static final Role PR = NeopixelBot.SERVER.getRoleById(1012849076653527173L).orElseThrow();
    public static final Role PYTHON = NeopixelBot.SERVER.getRoleById(994686195072254002L).orElseThrow();
    public static final Role JAVA = NeopixelBot.SERVER.getRoleById(994686225497735280L).orElseThrow();
    public static final Role JAVASCRIPT = NeopixelBot.SERVER.getRoleById(994686258712432741L).orElseThrow();
    public static final Role RUST = NeopixelBot.SERVER.getRoleById(995273194971877396L).orElseThrow();
}
