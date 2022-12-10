//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.awt.*;

public class notification
{
    public static void main(final String text) throws Exception {
        if (SystemTray.isSupported()) {
            final SystemTray systemTray = SystemTray.getSystemTray();
            final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("images/bor.png"));
            systemTray.add(trayIcon);
            trayIcon.displayMessage("MoonLight", text, TrayIcon.MessageType.INFO);
        }
    }
}
