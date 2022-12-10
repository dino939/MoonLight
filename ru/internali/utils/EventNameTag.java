//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.entity.*;

public class EventNameTag extends Event
{
    private String renderedName;
    private final EntityLivingBase entity;
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public EventNameTag(final EntityLivingBase entity, String s) {
        this.entity = entity;
        s = s;
    }
    
    public static void setRenderedName(String s) {
        s = s;
    }
    
    public String getRenderedName() {
        return this.renderedName;
    }
}
