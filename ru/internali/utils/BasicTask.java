//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public abstract class BasicTask implements Task
{
    private String name;
    private int priority;
    private boolean online;
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    @Override
    public void setOnline(final boolean online) {
        this.online = online;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public BasicTask(final String name, final int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    @Override
    public boolean isOnline() {
        return this.online;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPriority() {
        return this.priority;
    }
}
