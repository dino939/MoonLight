//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;

public abstract class Command
{
    public static String prefix;
    public static final Minecraft mc;
    
    public abstract void onClientCommand(final String p0, final String[] p1) throws Exception;
    
    public static void sendClientSideMessage(final String str) {
        if (Command.mc.player == null || Command.mc.world == null) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("[MoonLight] ").append(ChatFormatting.WHITE).append(str))));
    }
    
    public static String getClientPrefix() {
        return Command.prefix;
    }
    
    public static void setClientPrefix(final String prefix) {
        Command.prefix = prefix;
    }
    
    public abstract String getClientSyntax();
    
    static {
        mc = Minecraft.getMinecraft();
        Command.prefix = ".";
    }
    
    public abstract String[] getClientAlias();
}
