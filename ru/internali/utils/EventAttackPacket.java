//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.entity.*;

public class EventAttackPacket extends EventCancellable
{
    private final Entity targetEntity;
    
    public boolean cancel() {
        return false;
    }
    
    public Entity getTargetEntity() {
        return this.targetEntity;
    }
    
    public EventAttackPacket(final Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}
