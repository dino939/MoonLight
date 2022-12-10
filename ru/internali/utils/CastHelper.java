//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.internali.utils.friend.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;

public class CastHelper
{
    private final List casts;
    
    public CastHelper() {
        this.casts = new ArrayList();
    }
    
    public CastHelper apply(final EntityType entityType) {
        this.casts.add(entityType);
        return this;
    }
    
    public static EntityType isInstanceof(final Entity entity, final EntityType... array) {
        for (final EntityType entityType : array) {
            if (entity == Minecraft.getMinecraft().player && entityType == EntityType.SELF) {
                return entityType;
            }
            if (entityType == EntityType.VILLAGERS && entity instanceof EntityVillager) {
                return entityType;
            }
            if (entityType == EntityType.FRIENDS && entity instanceof EntityPlayer && FriendManager.isFriend(entity.getName())) {
                return entityType;
            }
            if (entityType == EntityType.PLAYERS && entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().player && !FriendManager.isFriend(entity.getName())) {
                return entityType;
            }
            if (entityType == EntityType.MOBS && entity instanceof EntityMob) {
                return entityType;
            }
            if (entityType == EntityType.ANIMALS && (entity instanceof EntityAnimal || entity instanceof EntityGolem)) {
                return entityType;
            }
        }
        return null;
    }
    
    public EntityType[] build() {
        return this.casts.toArray(new EntityType[0]);
    }
    
    public enum EntityType
    {
        ANIMALS("ANIMALS", 2), 
        PLAYERS("PLAYERS", 0), 
        SELF("SELF", 5), 
        FRIENDS("FRIENDS", 4), 
        VILLAGERS("VILLAGERS", 3), 
        MOBS("MOBS", 1);
        
        private static final EntityType[] $VALUES;
        
        private EntityType(final String name, final int ordinal) {
        }
        
        static {
            $VALUES = new EntityType[] { EntityType.PLAYERS, EntityType.MOBS, EntityType.ANIMALS, EntityType.VILLAGERS, EntityType.FRIENDS, EntityType.SELF };
        }
    }
}
