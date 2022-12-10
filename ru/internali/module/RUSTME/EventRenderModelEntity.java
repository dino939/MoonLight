//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.RUSTME;

import ru.internali.utils.*;
import net.minecraft.entity.*;

public class EventRenderModelEntity extends Event implements IEventCancelable
{
    private Type type;
    private boolean canceled;
    private Entity entity;
    
    public Type getType() {
        return this.type;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
    
    @Override
    public void setCanceled() {
        this.canceled = true;
    }
    
    @Override
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public EventRenderModelEntity(final Entity entity, final Type type) {
        this.entity = entity;
        this.type = type;
    }
    
    public enum Type
    {
        POST("POST", 1), 
        PRE("PRE", 0);
        
        private static final Type[] $VALUES;
        
        private Type(final String name, final int ordinal) {
        }
        
        static {
            $VALUES = new Type[] { Type.PRE, Type.POST };
        }
    }
}
