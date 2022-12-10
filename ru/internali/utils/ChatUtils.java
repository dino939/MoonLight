//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.util.text.*;
import ru.internali.*;
import ru.internali.module.*;
import java.util.*;

public class ChatUtils
{
    public static void simpleMessage(final Object o) {
        component((ITextComponent)new TextComponentTranslation((String)o, new Object[0]));
    }
    
    public static void component(final ITextComponent textComponent) {
        if (Wrapper.INSTANCE.player() == null || Wrapper.INSTANCE.mc().ingameGUI.getChatGUI() == null) {
            return;
        }
        Wrapper.INSTANCE.mc().ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation(String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append("")), new Object[0]).appendSibling(textComponent));
    }
    
    public static void complete(final Object obj) {
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModuleList().iterator();
        while (iterator.hasNext()) {
            component((ITextComponent)new TextComponentTranslation(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("[").append(TextFormatting.LIGHT_PURPLE).append(iterator.next().getName()).append(TextFormatting.GRAY).append("] ").append(obj)), new Object[0]));
        }
    }
    
    public static void message(final Object obj) {
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModuleList().iterator();
        while (iterator.hasNext()) {
            component((ITextComponent)new TextComponentTranslation(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("[").append(TextFormatting.WHITE).append(iterator.next().getName()).append(TextFormatting.GRAY).append("] ").append(obj)), new Object[0]));
        }
    }
    
    public static void error(final Object obj) {
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModuleList().iterator();
        while (iterator.hasNext()) {
            component((ITextComponent)new TextComponentTranslation(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("[").append(TextFormatting.RED).append(iterator.next().getName()).append(TextFormatting.GRAY).append("] ").append(obj)), new Object[0]));
        }
    }
    
    public static void warning(final Object obj) {
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModuleList().iterator();
        while (iterator.hasNext()) {
            component((ITextComponent)new TextComponentTranslation(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("[").append(TextFormatting.GOLD).append(iterator.next().getName()).append(TextFormatting.GRAY).append("] ").append(obj)), new Object[0]));
        }
    }
}
