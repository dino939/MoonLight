//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.util.*;

public class EnemyManager
{
    public static ArrayList enemysList;
    public static ArrayList detects;
    public static ArrayList murders;
    
    public static void removeEnemy(final String s) {
        if (EnemyManager.enemysList.contains(s)) {
            EnemyManager.enemysList.remove(s);
        }
    }
    
    static {
        EnemyManager.enemysList = new ArrayList();
        EnemyManager.murders = new ArrayList();
        EnemyManager.detects = new ArrayList();
    }
    
    public static void addEnemy(final String s) {
        if (!EnemyManager.enemysList.contains(s)) {
            EnemyManager.enemysList.add(s);
        }
    }
    
    public static void clear() {
        if (!EnemyManager.enemysList.isEmpty()) {
            EnemyManager.enemysList.clear();
        }
    }
}
