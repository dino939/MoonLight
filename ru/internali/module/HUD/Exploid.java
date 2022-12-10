//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

public class Exploid
{
    double posZ;
    String name;
    double posY;
    double posX;
    
    public String getName() {
        return this.name;
    }
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public double getPosY() {
        return this.posY;
    }
    
    public Exploid(final String name, final double posX, final double posY, final double posZ) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    
    public double getPosX() {
        return this.posX;
    }
}
