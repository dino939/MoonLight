//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class PlayerMoveEvent extends Event
{
    public static double x;
    public static double z;
    public double y;
    
    public PlayerMoveEvent(final double x, final double y, final double z) {
        PlayerMoveEvent.x = x;
        this.y = y;
        PlayerMoveEvent.z = z;
    }
}
