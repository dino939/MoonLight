//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraftforge.client;

import net.minecraftforge.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.command.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.util.*;
import net.minecraft.server.*;

public class ClientCommandHandler extends CommandHandler
{
    public static final ClientCommandHandler instance;
    public String[] latestAutoComplete;
    
    public ClientCommandHandler() {
        this.latestAutoComplete = null;
    }
    
    public int executeCommand(final ICommandSender commandSender, String s) {
        s = s.trim();
        final boolean startsWith = s.startsWith("/");
        if (startsWith) {
            s = s.substring(1);
        }
        final String[] split = s.split(" ");
        final String[] array = new String[split.length - 1];
        final String s2 = split[0];
        System.arraycopy(split, 1, array, 0, array.length);
        final ICommand command = this.getCommands().get(s2);
        try {
            if (command == null || (!startsWith && command instanceof IClientCommand && !((IClientCommand)command).allowUsageWithoutPrefix(commandSender, s))) {
                return 0;
            }
            if (command.checkPermission(this.getServer(), commandSender)) {
                final CommandEvent commandEvent = new CommandEvent(command, commandSender, array);
                if (!MinecraftForge.EVENT_BUS.post((Event)commandEvent)) {
                    this.tryExecute(commandSender, array, command, s);
                    return 1;
                }
                if (commandEvent.getException() != null) {
                    throw commandEvent.getException();
                }
                return 0;
            }
            else {
                commandSender.sendMessage((ITextComponent)this.format(TextFormatting.RED, "commands.generic.permission", new Object[0]));
            }
        }
        catch (WrongUsageException ex) {
            commandSender.sendMessage((ITextComponent)this.format(TextFormatting.RED, "commands.generic.usage", this.format(TextFormatting.RED, ex.getMessage(), ex.getErrorObjects())));
        }
        catch (CommandException ex2) {
            commandSender.sendMessage((ITextComponent)this.format(TextFormatting.RED, ex2.getMessage(), ex2.getErrorObjects()));
        }
        catch (Throwable t) {
            commandSender.sendMessage((ITextComponent)this.format(TextFormatting.RED, "commands.generic.exception", new Object[0]));
            FMLLog.log.error("Command '{}' threw an exception:", (Object)s, (Object)t);
        }
        return -1;
    }
    
    private TextComponentTranslation format(final TextFormatting textFormatting, final String s, final Object... array) {
        final TextComponentTranslation textComponentTranslation = new TextComponentTranslation(s, array);
        textComponentTranslation.getStyle().setColor(textFormatting);
        return textComponentTranslation;
    }
    
    public void autoComplete(String substring) {
        this.latestAutoComplete = null;
        if (substring.charAt(0) == '/') {
            substring = substring.substring(1);
            final Minecraft client = FMLClientHandler.instance().getClient();
            if (client.currentScreen instanceof GuiChat) {
                final List getTabCompletions = this.getTabCompletions((ICommandSender)client.player, substring, client.player.getPosition());
                if (!getTabCompletions.isEmpty()) {
                    if (substring.indexOf(32) == -1) {
                        for (int i = 0; i < getTabCompletions.size(); ++i) {
                            getTabCompletions.set(i, TextFormatting.GRAY + "/" + getTabCompletions.get(i) + TextFormatting.RESET);
                        }
                    }
                    else {
                        for (int j = 0; j < getTabCompletions.size(); ++j) {
                            getTabCompletions.set(j, TextFormatting.GRAY + getTabCompletions.get(j) + TextFormatting.RESET);
                        }
                    }
                    this.latestAutoComplete = getTabCompletions.toArray(new String[getTabCompletions.size()]);
                }
            }
        }
    }
    
    protected MinecraftServer getServer() {
        return (MinecraftServer)Minecraft.getMinecraft().getIntegratedServer();
    }
    
    static {
        instance = new ClientCommandHandler();
    }
}
