//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.utils.*;

public class EventPlayerTravel extends Event
{
    public float strafe;
    public float forward;
    public float vertical;
    
    public EventPlayerTravel(final float strafe, final float vertical, final float forward) {
        this.strafe = strafe;
        this.vertical = vertical;
        this.forward = forward;
    }
}
